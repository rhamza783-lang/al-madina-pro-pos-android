package com.almadina.pos.repository

import com.almadina.pos.data.local.ItemDao
import com.almadina.pos.model.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(private val itemDao: ItemDao) {
    // This now fetches REAL data from the local database.
    fun getAvailableItems(): Flow<List<Item>> {
        return itemDao.getAvailableItems()
    }
}
