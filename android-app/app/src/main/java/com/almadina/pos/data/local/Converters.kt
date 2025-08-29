package com.almadina.pos.data.local

import androidx.room.TypeConverter
import com.almadina.pos.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // --- ENUM CONVERTERS ---
    @TypeConverter
    fun fromSyncStatus(status: SyncStatus?): String? = status?.name
    @TypeConverter
    fun toSyncStatus(value: String?): SyncStatus? = value?.let { SyncStatus.valueOf(it) }

    @TypeConverter
    fun fromPaymentStatus(status: PaymentStatus?): String? = status?.name
    @TypeConverter
    fun toPaymentStatus(value: String?): PaymentStatus? = value?.let { PaymentStatus.valueOf(it) }

    // --- LIST CONVERTERS ---
    @TypeConverter
    fun fromOrderItemList(value: List<OrderItem>?): String = gson.toJson(value)
    @TypeConverter
    fun toOrderItemList(value: String): List<OrderItem>? {
        return gson.fromJson(value, object : TypeToken<List<OrderItem>?>() {}.type)
    }
    
    @TypeConverter
    fun fromPaymentList(value: List<Payment>?): String = gson.toJson(value)
    @TypeConverter
    fun toPaymentList(value: String): List<Payment>? {
        return gson.fromJson(value, object : TypeToken<List<Payment>?>() {}.type)
    }
    
    // ... (other list converters can go here if needed)
}
