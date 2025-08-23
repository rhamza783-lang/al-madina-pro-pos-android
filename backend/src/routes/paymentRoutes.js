const express = require('express');
const router = express.Router();
const paymentController = require('../controllers/paymentController');

// @route   POST api/payments/:orderId
// @desc    Process a payment for a specific order
// @access  Private (Protected by authMiddleware)
router.post('/:orderId', paymentController.processPayment);

module.exports = router;
