const express = require('express');
const router = express.Router();
const orderController = require('../controllers/orderController');

// --- Order Routes ---
router.post('/', orderController.createOrder);
router.get('/today', orderController.getTodayOrders);
router.get('/:orderId', orderController.getOrderById);
router.post('/:orderId/items', orderController.addItemToOrder);

module.exports = router;
