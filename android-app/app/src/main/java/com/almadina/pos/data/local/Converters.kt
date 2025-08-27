package com.almadina.pos.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.almadina.pos.model.*

class Converters {
    private val gson = Gson()

    // === ModifierGroup (List<ModifierGroup>) ===
    @TypeConverter
    fun fromModifierGroups(modifierGroups: List<ModifierGroup>?): String? {
        return modifierGroups?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toModifierGroups(json: String?): List<ModifierGroup>? {
        return json?.let {
            val type = object : TypeToken<List<ModifierGroup>>() {}.type
            gson.fromJson(it, type)
        }
    }

    // === OrderItem (List<OrderItem>) ===
    @TypeConverter
    fun fromOrderItems(items: List<OrderItem>?): String? {
        return items?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toOrderItems(json: String?): List<OrderItem>? {
        return json?.let {
            val type = object : TypeToken<List<OrderItem>>() {}.type
            gson.fromJson(it, type)
        }
    }

    // === Payment (List<Payment>) ===
    @TypeConverter
    fun fromPayments(payments: List<Payment>?): String? {
        return payments?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toPayments(json: String?): List<Payment>? {
        return json?.let {
            val type = object : TypeToken<List<Payment>>() {}.type
            gson.fromJson(it, type)
        }
    }

    // === PaymentStatus (enum) ===
    @TypeConverter
    fun fromPaymentStatus(status: PaymentStatus?): String? {
        return status?.name
    }

    @TypeConverter
    fun toPaymentStatus(name: String?): PaymentStatus? {
        return name?.let { PaymentStatus.valueOf(it) }
    }

    // === OrderStatus (enum) ===
    @TypeConverter
    fun fromOrderStatus(status: OrderStatus?): String? {
        return status?.name
    }

    @TypeConverter
    fun toOrderStatus(name: String?): OrderStatus? {
        return name?.let { OrderStatus.valueOf(it) }
    }

    // === PaymentMethod (enum) ===
    @TypeConverter
    fun fromPaymentMethod(method: PaymentMethod?): String? {
        return method?.name
    }

    @TypeConverter
    fun toPaymentMethod(name: String?): PaymentMethod? {
        return name?.let { PaymentMethod.valueOf(it) }
    }

    // === TableStatus (enum) ===
    @TypeConverter
    fun fromTableStatus(status: TableStatus?): String? {
        return status?.name
    }

    @TypeConverter
    fun toTableStatus(name: String?): TableStatus? {
        return name?.let { TableStatus.valueOf(it) }
    }

    // === UserRole (enum) ===
    @TypeConverter
    fun fromUserRole(role: UserRole?): String? {
        return role?.name
    }

    @TypeConverter
    fun toUserRole(name: String?): UserRole? {
        return name?.let { UserRole.valueOf(it) }
    }
}
