package com.almadina.pos.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almadina.pos.model.User
import com.almadina.pos.repository.AuthRepository
import com.almadina.pos.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = combine(
        orderRepository.getTodayTotalSales(),
        orderRepository.getTodayOrderCount()
    ) { sales, orderCount ->
        DashboardUiState(
            todaySales = sales ?: 0.0,
            todayOrders = orderCount
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardUiState()
    )

    private val _currentUser = MutableStateFlow(authRepository.getCurrentUser())
    val currentUser = _currentUser.asStateFlow()
}

data class DashboardUiState(
    val todaySales: Double = 0.0,
    val todayOrders: Int = 0,
)
