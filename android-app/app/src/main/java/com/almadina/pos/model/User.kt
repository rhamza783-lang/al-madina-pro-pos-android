package com.almadina.pos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.almadina.pos.data.local.Converters

@Entity(tableName = "users")
@TypeConverters(Converters::class)
data class User(
    @PrimaryKey
    val id: String,
    val staffId: String,
    val name: String,
    val role: UserRole,
    val isActive: Boolean = true
)

enum class UserRole {
    ADMIN, MANAGER, CASHIER, WAITER;

    val canAccessSettings: Boolean
        get() = this == ADMIN || this == MANAGER
}
