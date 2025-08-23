const express = require('express');
const router = express.Router();
const orderController = require('../controllers/orderController');
const paymentController = require('../controllers/paymentController');

// --- Order Routes ---
router.post('/', orderController.createOrder);
router.get('/today', orderController.getTodayOrders);
router.get('/:orderId', orderController.getOrderById);
router.post('/:orderId/items', orderController.addItemToOrder);

// --- Payment Route ---
// Note: Payment is a sub-resource of an order.
router.post('/:orderId/payment', paymentController.processPayment);

module.exports = router;
