const { DataTypes } = require('sequelize');
const { sequelize } = require('./sequelize');

const Payment = sequelize.define('Payment', {
  id: {
    type: DataTypes.UUID,
    defaultValue: DataTypes.UUIDV4,
    primaryKey: true
  },
  order_id: {
    type: DataTypes.UUID,
    allowNull: false
  },
  method: {
    type: DataTypes.ENUM('CASH', 'JAZZCASH', 'EASYPAISA', 'CARD', 'CREDIT'),
    allowNull: false
  },
  amount: {
    type: DataTypes.DECIMAL(10, 2),
    allowNull: false
  },
  reference_number: {
    type: DataTypes.STRING,
    allowNull: true
  }
}, {
  tableName: 'payments',
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: 'updated_at' // Sequelize adds this by default if timestamps: true
});

module.exports = Payment;
