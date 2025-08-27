package com.almadina.pos.data.local

import androidx.room.*
import com.almadina.pos.model.Category
import com.almadina.pos.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Category>)

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Item>)

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Item>
}
