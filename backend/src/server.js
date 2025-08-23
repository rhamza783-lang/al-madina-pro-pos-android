// Load environment variables from .env file
require('dotenv').config({ path: '../.env' });

const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const { sequelize } = require('./models');

// Import routes
const authRoutes = require('./routes/authRoutes');
const orderRoutes = require('./routes/orderRoutes');
const paymentRoutes = require('./routes/paymentRoutes');
const reportRoutes = require('./routes/reportRoutes');
// Import middleware
const { authMiddleware } = require('./middleware/authMiddleware');

const app = express();
const PORT = process.env.PORT || 3000;

// Global Middleware
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// --- API Routes ---

// Public route for authentication
app.use('/api/auth', authRoutes);

// Protected routes that require a valid JWT
app.use('/api/orders', authMiddleware, orderRoutes);
app.use('/api/payments', authMiddleware, paymentRoutes);
app.use('/api/reports', authMiddleware, reportRoutes);

// Health check endpoint
app.get('/health', (req, res) => {
  res.status(200).json({ status: 'OK', timestamp: new Date().toISOString() });
});

// --- Server & Database Initialization ---
const startServer = async () => {
  try {
    await sequelize.authenticate();
    console.log('Database connection has been established successfully.');

    // Sync all models with the database
    await sequelize.sync({ alter: true });
    console.log('All models were synchronized successfully.');

    app.listen(PORT, () => {
      console.log(`Server is running on http://localhost:${PORT}`);
    });
  } catch (error) {
    console.error('Unable to start the server:', error);
  }
};

startServer();
