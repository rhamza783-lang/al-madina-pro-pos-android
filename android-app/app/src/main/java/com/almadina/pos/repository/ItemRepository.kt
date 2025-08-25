package com.almadina.pos.repository

import com.almadina.pos.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor() {

    // In a real app, this would come from an ItemDao or an ApiService
    private val mockItems = listOf(
        Item(id = UUID.randomUUID().toString(), name = "Chicken Karahi", categoryId = "1", categoryName = "Main", price = 1200.0),
        Item(id = UUID.randomUUID().toString(), name = "Mutton Karahi", categoryId = "1", categoryName = "Main", price = 1800.0),
        Item(id = UUID.randomUUID().toString(), name = "Chicken Biryani", categoryId = "1", categoryName = "Main", price = 400.0),
        Item(id = UUID.randomUUID().toString(), name = "Chicken Tikka", categoryId = "2", categoryName = "BBQ", price = 450.0),
        Item(id = UUID.randomUUID().toString(), name = "Seekh Kabab", categoryId = "2", categoryName = "BBQ", price = 400.0),
        Item(id = UUID.randomUUID().toString(), name = "Lassi", categoryId = "3", categoryName = "Beverages", price = 100.0),
        Item(id = UUID.randomUUID().toString(), name = "Soft Drink", categoryId = "3", categoryName = "Beverages", price = 80.0),
        Item(id = UUID.randomUUID().toString(), name = "Gulab Jamun", categoryId = "4", categoryName = "Desserts", price = 150.0)
    )

    fun getAvailableItems(): Flow<List<Item>> {
        return flowOf(mockItems)
    }
}
