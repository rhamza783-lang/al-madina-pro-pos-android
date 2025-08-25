package com.almadina.pos.ui.inventory

import androidx.lifecycle.ViewModel
import com.almadina.pos.model.InventoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    // In a real app, you would inject an InventoryRepository here
) : ViewModel() {

    private val _inventoryItems = MutableStateFlow<List<InventoryItem>>(
        listOf(
            InventoryItem(UUID.randomUUID().toString(), "Chicken", "kg", 15.5, 20.0, 550.0, null, null, null),
            InventoryItem(UUID.randomUUID().toString(), "Rice", "kg", 80.0, 50.0, 250.0, null, null, null),
            InventoryItem(UUID.randomUUID().toString(), "Onions", "kg", 35.0, 20.0, 120.0, null, null, null)
        )
    )
    val inventoryItems = _inventoryItems.asStateFlow()

    // You would add functions here to adjust stock, create purchase orders, etc.
}
