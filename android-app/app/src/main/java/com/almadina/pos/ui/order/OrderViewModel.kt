package com.almadina.pos.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almadina.pos.model.Item
import com.almadina.pos.model.Order
import com.almadina.pos.repository.AuthRepository
import com.almadina.pos.repository.ItemRepository
import com.almadina.pos.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    private val _currentOrder = MutableStateFlow<Order?>(null)
    val currentOrder: StateFlow<Order?> = _currentOrder.asStateFlow()

    val menuItems: StateFlow<List<Item>> = itemRepository.getAvailableItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun loadOrCreateOrder(tableNumber: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val existingOrder = orderRepository.getActiveOrderForTable(tableNumber)
            if (existingOrder != null) {
                _currentOrder.value = existingOrder
            } else {
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    val newOrder = orderRepository.createOrder(
                        tableNumber = tableNumber,
                        waiterId = currentUser.id,
                        waiterName = currentUser.name
                    )
                    _currentOrder.value = newOrder
                } else {
                    // Handle error: No user logged in
                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun addItemToOrder(item: Item) {
        viewModelScope.launch {
            _currentOrder.value?.let { order ->
                val updatedOrder = orderRepository.addItemToOrder(
                    orderId = order.id,
                    item = item,
                    quantity = 1, // Simplified for now
                    modifiers = emptyList() // Simplified for now
                )
                _currentOrder.value = updatedOrder
            }
        }
    }
}

data class OrderUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
