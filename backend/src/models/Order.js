const { DataTypes } = require('sequelize');
const { sequelize } = require('./sequelize');

const Order = sequelize.define('Order', {
  id: {
    type: DataTypes.UUID,
    defaultValue: DataTypes.UUIDV4,
    primaryKey: true
  },
  table_number: {
    type: DataTypes.INTEGER,
    allowNull: false
  },
  waiter_id: {
    type: DataTypes.UUID,
    allowNull: false
  },
  waiter_name: {
    type: DataTypes.STRING,
    allowNull: false
  },
  total_amount: {
    type: DataTypes.DECIMAL(10, 2),
    defaultValue: 0
  },
  payment_status: {
    type: DataTypes.ENUM('PENDING', 'PARTIAL', 'PAID', 'REFUNDED'),
    defaultValue: 'PENDING'
  },
  order_status: {
    type: DataTypes.ENUM('ACTIVE', 'COMPLETED', 'CANCELLED', 'VOID'),
    defaultValue: 'ACTIVE'
  },
  invoice_number: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true
  }
}, {
  tableName: 'orders',
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: 'updated_at'
});

module.exports = Order;
