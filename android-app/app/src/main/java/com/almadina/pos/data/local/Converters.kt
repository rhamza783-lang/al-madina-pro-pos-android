package com.almadina.pos.data.local

import androidx.room.TypeConverter
import com.almadina.pos.model.ItemModifier
import com.almadina.pos.model.ModifierGroup
import com.almadina.pos.model.OrderItem
import com.almadina.pos.model.Payment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

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

    @TypeConverter
    fun fromItemModifierList(value: List<ItemModifier>?): String = gson.toJson(value)

    @TypeConverter
    fun toItemModifierList(value: String): List<ItemModifier>? {
        return gson.fromJson(value, object : TypeToken<List<ItemModifier>?>() {}.type)
    }

    @TypeConverter
    fun fromModifierGroupList(value: List<ModifierGroup>?): String = gson.toJson(value)

    @TypeConverter
    fun toModifierGroupList(value: String): List<ModifierGroup>? {
        return gson.fromJson(value, object : TypeToken<List<ModifierGroup>?>() {}.type)
    }
}
