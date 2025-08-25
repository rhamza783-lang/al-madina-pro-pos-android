package com.almadina.pos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.almadina.pos.model.Category
import com.almadina.pos.model.Item
import com.almadina.pos.model.Order
import com.almadina.pos.model.Table
import com.almadina.pos.model.User

@Database(
    entities = [Order::class, Item::class, Category::class, User::class, Table::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun orderDao(): OrderDao
    abstract fun itemDao(): ItemDao
    abstract fun userDao(): UserDao
    abstract fun tableDao(): TableDao
    
    companion object {
        const val DATABASE_NAME = "almadina_pos_database"
    }
}
