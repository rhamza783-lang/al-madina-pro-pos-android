package com.almadina.pos.data.remote

import com.almadina.pos.model.Order
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// A simplified ApiService. You would add more endpoints as needed.
interface ApiService {

    // Example: POST /api/orders
    @POST("orders")
    suspend fun createOrder(@Body order: Order): Order

    // Example: GET /api/orders/today
    @GET("orders/today")
    suspend fun getTodayOrders(): List<Order>
    
    // Example: POST /api/payments/{orderId}
    @POST("payments/{orderId}")
    suspend fun processPayment(@Path("orderId") orderId: String, @Body paymentData: Any): Order
}
