package com.almadina.pos.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almadina.pos.model.Order
import com.almadina.pos.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
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
            // --- THIS IS THE FIX ---
            // Use firstOrNull() to get the first emitted list, then find the order
            val order = orderRepository.getAllOrdersStream().firstOrNull()
                ?.find { it.id == orderId } // Use 'it' here
            
            _uiState.update { it.copy(isLoading = false, order = order) }
        }
    }

    fun processPayment(receivedAmount: Double, onPaymentComplete: () -> Unit) {
        viewModelScope.launch {
            _uiState.value.order?.let {
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
