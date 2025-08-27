package com.almadina.pos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.almadina.pos.data.local.Converters
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

// Order Entity
@Parcelize
@Entity(tableName = "orders")
@TypeConverters(Converters::class)
data class Order(
    @PrimaryKey
    val id: String,
    val tableNumber: Int,
    val waiterId: String,
    val waiterName: String,
    val cashierId: String? = null,
    val cashierName: String? = null,
    val totalAmount: Double,
    val subtotal: Double,
    val taxAmount: Double,
    val discountAmount: Double,
    val receivedAmount: Double = 0.0,
    val changeReturned: Double = 0.0,
    val balanceDue: Double = 0.0,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val orderStatus: OrderStatus = OrderStatus.ACTIVE,
    val invoiceNumber: String,
    val items: List<OrderItem>,
    val payments: List<Payment> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

// Order Item (part of Order)
@Parcelize
data class OrderItem(
    val id: String,
    val itemId: String,
    val itemName: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double,
    val modifiers: List<ItemModifier> = emptyList(),
    val notes: String? = null
) : Parcelable

// Item Modifier
@Parcelize
data class ItemModifier(
    val id: String,
    val name: String,
    val price: Double
) : Parcelable

// Payment
@Parcelize
data class Payment(
    val id: String,
    val method: PaymentMethod,
    val amount: Double,
    val referenceNumber: String? = null,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

// Enums must be converted to String or Int
enum class PaymentStatus { PENDING, PARTIAL, PAID, REFUNDED }
enum class OrderStatus { ACTIVE, COMPLETED, CANCELLED }
enum class PaymentMethod { CASH, JAZZCASH, EASYPAISA, CARD, CREDIT }
