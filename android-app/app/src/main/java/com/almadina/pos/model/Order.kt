package com.almadina.pos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.almadina.pos.data.local.Converters
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

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
    val createdAt: Long = System.currentTimeMillis(),
    // This is now a real column that Room understands
    val syncStatus: SyncStatus = SyncStatus.PENDING
) : Parcelable

// ... (The rest of the Order.kt file is the same)
@Parcelize data class OrderItem(/*...*/) : Parcelable
@Parcelize data class ItemModifier(/*...*/) : Parcelable
@Parcelize data class Payment(/*...*/) : Parcelable
enum class PaymentStatus { PENDING, PARTIAL, PAID, REFUNDED }
enum class OrderStatus { ACTIVE, COMPLETED, CANCELLED }
// Add SyncStatus here
enum class SyncStatus { SYNCED, PENDING, FAILED }
