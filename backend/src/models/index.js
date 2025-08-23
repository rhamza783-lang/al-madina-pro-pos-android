const { sequelize } = require('./sequelize');
const User = require('./User');
const Item = require('./Item');
const Order = require('./Order');
const OrderItem = require('./OrderItem');
const Payment = require('./Payment');

// --- Define Relationships ---

// Order <-> OrderItem (One-to-Many)
Order.hasMany(OrderItem, { foreignKey: 'order_id', as: 'items' });
OrderItem.belongsTo(Order, { foreignKey: 'order_id' });

// Order <-> Payment (One-to-Many)
Order.hasMany(Payment, { foreignKey: 'order_id', as: 'payments' });
Payment.belongsTo(Order, { foreignKey: 'order_id' });

// User <-> Order (One-to-Many)
User.hasMany(Order, { foreignKey: 'waiter_id', as: 'waiterOrders' });
Order.belongsTo(User, { as: 'waiter', foreignKey: 'waiter_id' });

// --- Export All Models ---
module.exports = {
  sequelize,
  User,
  Item,
  Order,
  OrderItem,
  Payment
};
