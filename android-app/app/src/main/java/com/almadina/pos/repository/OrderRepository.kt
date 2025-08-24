package com.almadina.pos.repository

import com.almadina.pos.data.local.OrderDao
import com.almadina.pos.data.remote.ApiService
import com.almadina.pos.model.Item
import com.almadina.pos.model.ItemModifier
import com.almadina.pos.model.Order
import com.almadina.pos.model.SyncStatus
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao,
    private val apiService: ApiService
    // In a real app, you would inject a NetworkUtils class here
) {
    // --- Data Streams from Local DB ---
    fun getTodayTotalSales(): Flow<Double?> = orderDao.getTotalSalesStream()
    fun getTodayOrderCount(): Flow<Int> = orderDao.getOrderCountStream()
    fun getAllOrdersStream(): Flow<List<Order>> = orderDao.getAllOrdersStream()

    // --- Core Functions ---
    suspend fun getActiveOrderForTable(tableNumber: Int): Order? {
        return orderDao.getActiveOrderForTable(tableNumber)
    }

    suspend fun createOrder(tableNumber: Int, waiterId: String, waiterName: String): Order {
        val newOrder = Order(
            id = UUID.randomUUID().toString(),
            tableNumber = tableNumber,
            waiterId = waiterId,
            waiterName = waiterName,
            totalAmount = 0.0,
            subtotal = 0.0,
            taxAmount = 0.0,
            discountAmount = 0.0,
            invoiceNumber = "INV-${System.currentTimeMillis()}", // Simple invoice number
            items = emptyList()
        )
        orderDao.insertOrder(newOrder)
        // Here you would also try to sync with the backend using apiService
        return newOrder
    }

    suspend fun addItemToOrder(orderId: String, item: Item, quantity: Int, modifiers: List<ItemModifier>): Order {
        val order = orderDao.getOrderById(orderId) ?: throw Exception("Order not found")

        val modifierPrice = modifiers.sumOf { it.price }
        val itemTotalPrice = (item.price + modifierPrice) * quantity
        
        val newOrderItem = com.almadina.pos.model.OrderItem(
            id = UUID.randomUUID().toString(),
            itemId = item.id,
            itemName = item.name,
            quantity = quantity,
            unitPrice = item.price,
            totalPrice = itemTotalPrice,
            modifiers = modifiers
        )
        
        val updatedItems = order.items + newOrderItem
        val updatedOrder = order.copy(
            items = updatedItems,
            subtotal = updatedItems.sumOf { it.totalPrice },
            totalAmount = updatedItems.sumOf { it.totalPrice }, // Simplified total for now
            syncStatus = SyncStatus.PENDING
        )
        
        orderDao.updateOrder(updatedOrder)
        return updatedOrder
    }
}
