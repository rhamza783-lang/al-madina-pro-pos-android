package com.almadina.pos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "items")
data class Item(
    @PrimaryKey
    val id: String,
    val name: String,
    val categoryId: String,
    val categoryName: String,
    val price: Double,
    val isAvailable: Boolean = true,
    val modifierGroups: List<ModifierGroup> = emptyList()
) : Parcelable

@Parcelize
data class ModifierGroup(
    val id: String,
    val name: String,
    val modifiers: List<Modifier>
) : Parcelable

@Parcelize
data class Modifier(
    val id: String,
    val name: String,
    val price: Double = 0.0
) : Parcelable

@Parcelize
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey
    val id: String,
    val name: String
) : Parcelable
