const { Order, OrderItem, Item, sequelize } = require('../models');
const { generateInvoiceNumber } = require('../utils/invoiceGenerator');
const { Op } = require('sequelize');
require('dotenv').config({ path: '../../.env' });

exports.createOrder = async (req, res) => {
  try {
    const { tableNumber, waiterId, waiterName } = req.body;
    
    const existingOrder = await Order.findOne({
      where: { table_number: tableNumber, order_status: 'ACTIVE' }
    });
    
    if (existingOrder) {
      return res.status(200).json(existingOrder);
    }
    
    const newOrder = await Order.create({
      table_number: tableNumber,
      waiter_id: waiterId,
      waiter_name: waiterName,
      invoice_number: generateInvoiceNumber()
    });
    
    res.status(201).json(newOrder);
  } catch (error) {
    console.error("Create Order Error:", error);
    res.status(500).json({ error: 'Failed to create order.' });
  }
};

exports.addItemToOrder = async (req, res) => {
  const { orderId } = req.params;
  const { itemId, quantity, modifiers, notes } = req.body;
  const transaction = await sequelize.transaction();

  try {
    const order = await Order.findByPk(orderId, { transaction });
    const item = await Item.findByPk(itemId, { transaction });
    
    if (!order || !item) {
      await transaction.rollback();
      return res.status(404).json({ error: 'Order or Item not found.' });
    }
    
    const modifierTotal = (modifiers || []).reduce((sum, mod) => sum + (mod.price || 0), 0);
    const itemTotal = (parseFloat(item.price) + modifierTotal) * parseInt(quantity);
    
    const orderItem = await OrderItem.create({
      order_id: orderId,
      item_id: itemId,
      item_name: item.name,
      quantity,
      unit_price: item.price,
      total_price: itemTotal,
      modifiers,
      notes
    }, { transaction });
    
    const allItems = await OrderItem.findAll({ where: { order_id: orderId }, transaction });
    const subtotal = allItems.reduce((sum, currentItem) => sum + parseFloat(currentItem.total_price), 0);
    
    // IMPROVEMENT: Tax rate is now read from environment config
    const taxRate = parseFloat(process.env.TAX_RATE || 0.16);
    const taxAmount = subtotal * taxRate;
    
    order.subtotal = subtotal;
    order.tax_amount = taxAmount;
    order.total_amount = subtotal + taxAmount - (order.discount_amount || 0);
    await order.save({ transaction });
    
    await transaction.commit();
    res.status(201).json({ message: 'Item added successfully', orderItem });
  } catch (error) {
    await transaction.rollback();
    console.error("Add Item Error:", error);
    res.status(500).json({ error: 'Failed to add item to order.' });
  }
};

exports.getOrderById = async (req, res) => {
  try {
    const { orderId } = req.params;
    const order = await Order.findByPk(orderId, {
      include: [{ model: OrderItem, as: 'items' }, { model: Payment, as: 'payments' }]
    });
    
    if (!order) {
      return res.status(404).json({ error: 'Order not found.' });
    }
    
    res.status(200).json(order);
  } catch (error) {
    console.error("Get Order By ID Error:", error);
    res.status(500).json({ error: 'Failed to retrieve order.' });
  }
};

exports.getTodayOrders = async (req, res) => {
    try {
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        const orders = await Order.findAll({
            where: { created_at: { [Op.gte]: today } },
            include: [{ model: OrderItem, as: 'items' }],
            order: [['created_at', 'DESC']]
        });
        res.status(200).json(orders);
    } catch (error) {
        console.error("Get Today's Orders Error:", error);
        res.status(500).json({ error: "Failed to retrieve today's orders." });
    }
};
