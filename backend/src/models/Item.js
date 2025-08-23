const { DataTypes } = require('sequelize');
const { sequelize } = require('./sequelize');

const Item = sequelize.define('Item', {
  id: {
    type: DataTypes.UUID,
    defaultValue: DataTypes.UUIDV4,
    primaryKey: true
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false
  },
  price: {
    type: DataTypes.DECIMAL(10, 2),
    allowNull: false
  },
  category_id: {
    type: DataTypes.UUID,
    allowNull: false
  },
  is_available: {
    type: DataTypes.BOOLEAN,
    defaultValue: true
  }
}, {
  tableName: 'items',
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: 'updated_at'
});

module.exports = Item;
