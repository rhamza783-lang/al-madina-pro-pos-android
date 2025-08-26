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

    // --- NEW CONVERTERS FOR MODIFIERGROUP ---
    @TypeConverter
    fun fromModifierGroupList(value: List<ModifierGroup>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toModifierGroupList(value: String): List<ModifierGroup>? {
        val listType = object : TypeToken<List<ModifierGroup>?>() {}.type
        return gson.fromJson(value, listType)
    }
    // --- END OF NEW CONVERTERS ---

    // OrderItem List Converters
    @TypeConverter
    fun fromOrderItemList(value: List<OrderItem>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOrderItemList(value: String): List<OrderItem>? {
        val listType = object : TypeToken<List<OrderItem>?>() {}.type
        return gson.fromJson(value, listType)
    }

    // Payment List Converters
    @TypeConverter
    fun fromPaymentList(value: List<Payment>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPaymentList(value: String): List<Payment>? {
        // --- THIS IS THE LINE I FIXED ---
        val listType = object : TypeToken<List<Payment>?>() {}.type
        return gson.fromJson(value, listType)
    }
    
    // ItemModifier List Converters
    @TypeConverter
    fun fromItemModifierList(value: List<ItemModifier>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toItemModifierList(value: String): List<ItemModifier>? {
        val listType = object : TypeToken<List<ItemModifier>?>() {}.type
        return gson.fromJson(value, listType)
    }
}
