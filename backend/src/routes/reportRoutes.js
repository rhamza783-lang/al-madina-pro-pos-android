const express = require('express');
const router = express.Router();
const reportController = require('../controllers/reportController');

// @route   GET api/reports/daily
// @desc    Get a sales report for a specific day
// @access  Private
router.get('/daily', reportController.getDailySalesReport);

module.exports = router;
