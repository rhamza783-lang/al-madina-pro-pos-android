package com.almadina.pos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.almadina.pos.model.Category
import com.almadina.pos.model.Item
import com.almadina.pos.model.Order
import com.almadina.pos.model.User
import com.almadina.pos.model.Payment // IMPORTANT: Add this import

@Database(
    entities = [
        Order::class,
        Item::class,
        Category::class,
        User::class
        // Room does not need a separate table for Payment
        // because it's stored as JSON within the Order table via a TypeConverter.
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun orderDao(): OrderDao
    
    companion object {
        const val DATABASE_NAME = "almadina_pos_database"
    }
}
