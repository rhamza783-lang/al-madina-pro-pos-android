package com.almadina.pos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.almadina.pos.data.local.Converters
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

// Item Entity
@Parcelize
@Entity(tableName = "items")
@TypeConverters(Converters::class)
data class Item(
    @PrimaryKey
    val id: String,
    val name: String,
    val categoryId: String,
    val price: Double,
    val isAvailable: Boolean = true,
    val modifierGroups: List<ModifierGroup> = emptyList()
) : Parcelable

// Modifier Group (part of Item)
@Parcelize
data class ModifierGroup(
    val id: String,
    val name: String,
    val modifiers: List<Modifier>
) : Parcelable

// Modifier (leaf node)
@Parcelize
data class Modifier(
    val id: String,
    val name: String,
    val price: Double = 0.0
) : Parcelable

// Category Entity
@Parcelize
@Entity(tableName = "categories")
@TypeConverters(Converters::class)
data class Category(
    @PrimaryKey
    val id: String,
    val name: String
) : Parcelable
