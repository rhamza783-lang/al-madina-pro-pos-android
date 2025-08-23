-- Sample data for Al-Madina POS

-- Users (with HASHED PINs)
-- The 'pin' column now contains a bcrypt hash.
-- The hash below for '1234' is: $2a$10$fV/h558WvH.8P3.60a.bueg.N.b.d.B0D.N8.F.q.Z8/
-- You will need a script or backend endpoint to generate hashes for new users.
INSERT INTO users (staff_id, name, pin, role) VALUES
('1001', 'Ahmed Khan', '$2a$10$fV/h558WvH.8P3.60a.bueg.N.b.d.B0D.N8.F.q.Z8/', 'ADMIN'), -- PIN: 1234
('1002', 'Ali Hassan', '$2a$10$fV/h558WvH.8P3.60a.bueg.N.b.d.B0D.N8.F.q.Z8/', 'MANAGER'), -- PIN: 1234
('1003', 'Usman Ali', '$2a$10$fV/h558WvH.8P3.60a.bueg.N.b.d.B0D.N8.F.q.Z8/', 'CASHIER'), -- PIN: 1234
('1004', 'Bilal Ahmed', '$2a$10$fV/h558WvH.8P3.60a.bueg.N.b.d.B0D.N8.F.q.Z8/', 'WAITER'),  -- PIN: 1234
('1005', 'Hassan Raza', '$2a$10$fV/h558WvH.8P3.60a.bueg.N.b.d.B0D.N8.F.q.Z8/', 'WAITER');  -- PIN: 1234

-- Tables
INSERT INTO tables (number, section, capacity) VALUES
(1, 'Main Hall', 4),
(2, 'Main Hall', 4),
(3, 'Main Hall', 6),
(4, 'Main Hall', 6),
(5, 'Main Hall', 4),
(6, 'Main Hall', 4),
(7, 'Family Section', 6),
(8, 'Family Section', 6),
(9, 'Family Section', 8),
(10, 'Family Section', 8),
(11, 'Outdoor', 4),
(12, 'Outdoor', 4),
(13, 'Outdoor', 6),
(14, 'Outdoor', 6),
(15, 'VIP', 8),
(16, 'VIP', 10);

-- Categories
INSERT INTO categories (name, name_urdu, icon, color, sort_order) VALUES
('Starters', 'سٹارٹرز', 'appetizer', '#FF6B6B', 1),
('Main Course', 'مین کورس', 'main_dish', '#4ECDC4', 2),
('BBQ', 'باربی کیو', 'grill', '#FF8C42', 3),
('Beverages', 'مشروبات', 'drink', '#95E1D3', 4),
('Desserts', 'میٹھا', 'cake', '#F38181', 5);

-- Items
INSERT INTO items (name, name_urdu, category_id, price, preparation_time) VALUES
-- Starters
('Chicken Soup', 'چکن سوپ', (SELECT id FROM categories WHERE name = 'Starters'), 250, 10),
('French Fries', 'فرنچ فرائز', (SELECT id FROM categories WHERE name = 'Starters'), 150, 8),
('Chicken Wings', 'چکن ونگز', (SELECT id FROM categories WHERE name = 'Starters'), 350, 15),

-- Main Course
('Chicken Karahi', 'چکن کڑاہی', (SELECT id FROM categories WHERE name = 'Main Course'), 1200, 25),
('Mutton Karahi', 'مٹن کڑاہی', (SELECT id FROM categories WHERE name = 'Main Course'), 1800, 30),
('Chicken Biryani', 'چکن بریانی', (SELECT id FROM categories WHERE name = 'Main Course'), 400, 20),
('Dal Makhani', 'دال مکھنی', (SELECT id FROM categories WHERE name = 'Main Course'), 400, 15),

-- BBQ
('Chicken Tikka', 'چکن تکہ', (SELECT id FROM categories WHERE name = 'BBQ'), 450, 20),
('Seekh Kabab', 'سیخ کباب', (SELECT id FROM categories WHERE name = 'BBQ'), 400, 18),
('Malai Boti', 'ملائی بوٹی', (SELECT id FROM categories WHERE name = 'BBQ'), 500, 20),

-- Beverages
('Lassi', 'لسی', (SELECT id FROM categories WHERE name = 'Beverages'), 100, 5),
('Soft Drink', 'کولڈ ڈرنک', (SELECT id FROM categories WHERE name = 'Beverages'), 80, 2),
('Green Tea', 'سبز چائے', (SELECT id FROM categories WHERE name = 'Beverages'), 50, 5),

-- Desserts
('Gulab Jamun', 'گلاب جامن', (SELECT id FROM categories WHERE name = 'Desserts'), 150, 5),
('Kheer', 'کھیر', (SELECT id FROM categories WHERE name = 'Desserts'), 180, 5);

-- Inventory Items
INSERT INTO inventory_items (name, unit, current_stock, reorder_level) VALUES
('Chicken', 'kg', 50, 20),
('Mutton', 'kg', 30, 15),
('Rice', 'kg', 100, 50),
('Tomatoes', 'kg', 30, 15),
('Onions', 'kg', 40, 20),
('Milk', 'liters', 20, 10);

-- Sample Customers
INSERT INTO customers (name, phone, email, loyalty_points, total_spent) VALUES
('Muhammad Ali', '03001234567', 'ali@example.com', 150, 15000),
('Fatima Khan', '03211234567', 'fatima@example.com', 200, 20000);
