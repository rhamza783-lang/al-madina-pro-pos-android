package com.almadina.pos.data.local

import androidx.room.*
import com.almadina.pos.model.Order
import com.almadina.pos.model.OrderStatus
import com.almadina.pos.model.SyncStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)

    @Query("SELECT * FROM orders WHERE id = :orderId LIMIT 1")
    suspend fun getOrderById(orderId: String): Order?
    
    @Query("SELECT * FROM orders WHERE tableNumber = :tableNumber AND orderStatus = 'ACTIVE' LIMIT 1")
    suspend fun getActiveOrderForTable(tableNumber: Int): Order?

    @Query("SELECT * FROM orders WHERE syncStatus = :syncStatus")
    suspend fun getOrdersBySyncStatus(syncStatus: SyncStatus): List<Order>
    
    @Query("UPDATE orders SET syncStatus = :syncStatus WHERE id = :orderId")
    suspend fun updateSyncStatus(orderId: String, syncStatus: SyncStatus)
    
    // --- Reporting Queries ---
    
    @Query("SELECT * FROM orders ORDER BY createdAt DESC")
    fun getAllOrdersStream(): Flow<List<Order>>

    @Query("SELECT COUNT(id) FROM orders")
    fun getOrderCountStream(): Flow<Int>

    @Query("SELECT SUM(totalAmount) FROM orders WHERE orderStatus = 'COMPLETED'")
    fun getTotalSalesStream(): Flow<Double?>
}
