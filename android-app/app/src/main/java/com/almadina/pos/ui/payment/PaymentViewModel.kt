package com.almadina.pos.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almadina.pos.model.Order
import com.almadina.pos.model.Payment
import com.almadina.pos.model.PaymentMethod
import com.almadina.pos.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState = _uiState.asStateFlow()

    fun loadOrder(orderId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // In a real app, this would be a single DB call
            val order = orderRepository.getAllOrdersStream().replayCache.firstOrNull()
                ?.flatten()?.find { it.id == orderId }
            
            _uiState.update { it.copy(isLoading = false, order = order) }
        }
    }

    fun processPayment(receivedAmount: Double, onPaymentComplete: () -> Unit) {
        viewModelScope.launch {
            _uiState.value.order?.let { order ->
                // Here you would call a repository function to update the order
                // and sync with the backend.
                
                // For now, we just navigate away.
                onPaymentComplete()
            }
        }
    }
}

data class PaymentUiState(
    val isLoading: Boolean = false,
    val order: Order? = null,
    val errorMessage: String? = null
)
