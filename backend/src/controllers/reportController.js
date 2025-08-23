const { Order, OrderItem, Payment } = require('../models');
const { Op, fn, col, literal } = require('sequelize');

exports.getDailySalesReport = async (req, res) => {
  try {
    const { date } = req.query;
    const reportDate = date ? new Date(date) : new Date();
    reportDate.setHours(0, 0, 0, 0);
    
    const nextDay = new Date(reportDate);
    nextDay.setDate(nextDay.getDate() + 1);

    const whereClause = {
        created_at: {
            [Op.gte]: reportDate,
            [Op.lt]: nextDay
        },
        order_status: { [Op.in]: ['COMPLETED', 'ACTIVE'] }
    };
    
    const salesData = await Order.findOne({
      attributes: [
        [fn('COUNT', col('id')), 'totalOrders'],
        [fn('SUM', col('total_amount')), 'totalSales']
      ],
      where: whereClause
    });
    
    const paymentBreakdown = await Payment.findAll({
      attributes: [
        'method',
        [fn('SUM', col('amount')), 'total']
      ],
      include: [{
        model: Order,
        attributes: [],
        where: { created_at: { [Op.gte]: reportDate, [Op.lt]: nextDay } }
      }],
      group: ['method']
    });
    
    const topItems = await OrderItem.findAll({
      attributes: [
        'item_name',
        [fn('SUM', col('quantity')), 'totalQuantity'],
        [fn('SUM', col('total_price')), 'totalRevenue']
      ],
      include: [{ model: Order, attributes: [], where: whereClause }],
      group: ['item_name'],
      order: [[literal('totalQuantity'), 'DESC']],
      limit: 10
    });
    
    res.json({
      summary: {
        totalOrders: salesData.get('totalOrders') || 0,
        totalSales: salesData.get('totalSales') || 0,
      },
      paymentBreakdown,
      topItems
    });
  } catch (error) {
    console.error("Daily Sales Report Error:", error);
    res.status(500).json({ error: 'Failed to generate daily sales report.' });
  }
};
