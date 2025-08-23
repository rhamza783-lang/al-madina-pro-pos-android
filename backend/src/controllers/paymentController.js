const { Order, Payment, sequelize } = require('../models');

exports.processPayment = async (req, res) => {
  const { orderId } = req.params;
  const { payments, cashierId, cashierName } = req.body;
  const transaction = await sequelize.transaction();
  
  try {
    const order = await Order.findByPk(orderId, { transaction });
    if (!order) {
      await transaction.rollback();
      return res.status(404).json({ error: 'Order not found.' });
    }
    
    let totalPaid = parseFloat(order.received_amount || 0);
    
    for (const payment of payments) {
      await Payment.create({
        order_id: orderId,
        method: payment.method,
        amount: payment.amount,
        reference_number: payment.referenceNumber
      }, { transaction });
      
      totalPaid += parseFloat(payment.amount);
    }
    
    const orderTotal = parseFloat(order.total_amount);
    const changeReturned = Math.max(0, totalPaid - orderTotal);
    const balanceDue = Math.max(0, orderTotal - totalPaid);
    
    order.cashier_id = cashierId;
    order.cashier_name = cashierName;
    order.received_amount = totalPaid;
    order.change_returned = changeReturned;
    order.balance_due = balanceDue;
    order.payment_status = balanceDue === 0 ? 'PAID' : (totalPaid > 0 ? 'PARTIAL' : 'PENDING');
    
    if (balanceDue === 0) {
      order.order_status = 'COMPLETED';
    }
    
    await order.save({ transaction });
    await transaction.commit();
    
    res.status(200).json({ message: 'Payment processed successfully.', order });
  } catch (error) {
    await transaction.rollback();
    console.error("Process Payment Error:", error);
    res.status(500).json({ error: 'Failed to process payment.' });
  }
};
