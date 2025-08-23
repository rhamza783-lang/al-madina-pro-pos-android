# Al-Madina Pro POS System

A comprehensive, production-ready Point of Sale system for restaurants, featuring a feature-rich Android app (Kotlin & Jetpack Compose) and a secure Node.js backend.

## ✅ Features

### Core POS Features
- **Table Management**: Manage tables across multiple sections (Main Hall, Family, Outdoor) with real-time status updates (Available, Occupied, Bill Ready).
- **Order Taking**: Intuitive interface for adding items, complete with customizable modifiers and special kitchen notes.
- **Multiple Payment Methods**: Full support for Cash, JazzCash, Easypaisa, Card, and Customer Credit (Pay Later).
- **Split Payment Support**: Easily split a single bill across several payment methods.
- **Change Calculation**: Automatic and accurate change calculation for cash payments.
- **Kitchen Display System (KDS) Logic**: Functionality for sending new items directly to a kitchen printer.
- **Thermal Printer Support**: Print professional receipts and kitchen tickets via Bluetooth or Wi-Fi using the ESC/POS protocol.
- **PDF Invoice Generation**: Create and save detailed A4-sized invoices in PDF format.
- **WhatsApp Invoice Sharing**: Share PDF invoices directly with customers via WhatsApp with a single tap.
- **QR Code & Barcode on Receipts**: Each receipt includes a unique QR code and barcode for easy digital lookup and verification.

### Business & Analytics Features
- **Real-time Sales Dashboard**: A comprehensive dashboard showing today's sales, total orders, average ticket value, and active tables.
- **Advanced Analytics**: A dedicated dashboard with visual charts for revenue trends, hourly sales distribution, and payment method breakdowns.
- **Inventory Management**: Auto-deduction of stock items upon sale, with low stock alerts and detailed inventory value tracking.
- **Customer CRM & Loyalty**: Maintain a customer database, track spending, manage loyalty points, and handle credit balances.
- **Waiter Performance Tracking**: Generate reports to track the sales performance of individual staff members.
- **Offline Mode with Auto-sync**: The Android app is fully operational without an internet connection and automatically syncs all transactions when it comes back online.

### Security Features
- **Role-based Access Control**: Pre-defined roles (Admin, Manager, Cashier, Waiter) with specific permissions.
- **Secure Authentication**: Staff login via Staff ID and a secure, hashed PIN. Biometric (Fingerprint/Face ID) login support.
- **JWT Token Authentication**: Secure API communication between the app and the backend using JSON Web Tokens.
- **Automated Backups**: Configuration support for daily automated database backups.

## 💻 Technology Stack

### Android App
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (for a fully declarative, modern UI)
- **Architecture**: MVVM with Repository Pattern
- **Database**: Room (for robust offline storage)
- **Networking**: Retrofit & OkHttp
- **Dependency Injection**: Hilt
- **Printing**: ESC/POS Protocol Library
- **PDF Generation**: Native Android PDF API
- **QR/Barcode**: ZXing Library
- **Charting**: MPAndroidChart

### Backend
- **Runtime**: Node.js
- **Framework**: Express.js
- **Database**: PostgreSQL
- **ORM**: Sequelize
- **Authentication**: JWT (jsonwebtoken), Bcrypt (for secure PIN hashing)
- **API**: RESTful

## 🚀 Installation & Setup

### Backend Setup
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/al-madina-pro-pos.git
    cd al-madina-pro-pos/backend
    ```
2.  **Install dependencies**:
    ```bash
    npm install
    ```
3.  **Setup PostgreSQL database**:
    ```bash
    # Log in to psql
    psql -U postgres

    # Create the database
    CREATE DATABASE almadina_pos;
    \q
    ```
4.  **Run migrations and seed data**:
    ```bash
    psql -U postgres -d almadina_pos -f ../database/schema.sql
    psql -U postgres -d almadina_pos -f ../database/seed-data.sql
    ```
5.  **Configure environment**:
    ```bash
    # Create a .env file from the example
    cp .env.example .env

    # Edit the .env file with your database credentials and a new JWT_SECRET
    ```
6.  **Start the server**:
    ```bash
    npm start
    ```

### Android App Setup
1.  Open the `android-app` folder in Android Studio.
2.  Update the `BASE_URL` constant in `app/src/main/java/com/almadina/pos/data/remote/RetrofitClient.kt` to point to your server's IP address.
    ```kotlin
    const val BASE_URL = "http://YOUR_SERVER_IP_ADDRESS:3000/api/"
    ```
3.  Build and run the app on an Android device or emulator.

## 📄 API Usage Example

**Process a Payment**
`POST /api/payments/{orderId}`

**Headers:**
`Authorization: Bearer {your_jwt_token}`

**Body:**
```json
{
  "payments": [
    {"method": "CASH", "amount": 1000},
    {"method": "JAZZCASH", "amount": 524.50, "referenceNumber": "TRX123"}
  ],
  "cashierId": "a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6",
  "cashierName": "Usman Ali"
}
Deploying to Production
Backend
For a production environment, it is recommended to use a process manager like PM2 and a reverse proxy like Nginx.
# Install PM2 globally
npm install -g pm2

# Start the server with PM2
pm2 start src/server.js --name almadina-pos

# Ensure PM2 restarts on server reboot
pm2 save
pm2 startup
Support
For issues, feature requests, or support, please contact:
Email: support@almadina********
WhatsApp: +92-3*********
This project is proprietary software. All rights reserved.
