const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const { User } = require('../models');
require('dotenv').config({ path: '../../.env' });

// SECURITY FIX: This controller now uses bcrypt to securely handle PINs.
exports.login = async (req, res) => {
    try {
        const { staffId, pin } = req.body;
        if (!staffId || !pin) {
            return res.status(400).json({ error: 'Staff ID and PIN are required.' });
        }
        
        const user = await User.findOne({ where: { staff_id: staffId, is_active: true } });
        if (!user) {
            return res.status(401).json({ error: 'Invalid credentials or inactive user.' });
        }

        // Compare the provided PIN with the stored hash
        const isMatch = await bcrypt.compare(pin, user.pin);
        if (!isMatch) {
            return res.status(401).json({ error: 'Invalid credentials.' });
        }

        const payload = { id: user.id, name: user.name, role: user.role };
        
        jwt.sign(
            payload,
            process.env.JWT_SECRET,
            { expiresIn: process.env.JWT_EXPIRE || '7d' },
            (err, token) => {
                if (err) throw err;
                res.json({
                    message: "Login successful",
                    token: `Bearer ${token}`,
                    user: payload
                });
            }
        );
    } catch (error) {
        console.error('Login error:', error);
        res.status(500).json({ error: 'Server error during login.' });
    }
};
