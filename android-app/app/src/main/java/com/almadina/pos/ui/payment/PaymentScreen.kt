package com.almadina.pos.ui.payment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.almadina.pos.util.formatCurrency // <-- IMPORT ADDED

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    orderId: String,
    onPaymentComplete: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: PaymentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var cashReceived by remember { mutableStateOf("") }

    LaunchedEffect(orderId) {
        viewModel.loadOrder(orderId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Process Payment") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else if (uiState.order != null) {
                val order = uiState.order!!
                
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Order Summary", style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.height(12.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total Amount Due:")
                            Text(
                                formatCurrency(order.totalAmount),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = cashReceived,
                    onValueChange = { cashReceived = it },
                    label = { Text("Cash Received") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                
                val receivedAmount = cashReceived.toDoubleOrNull() ?: 0.0
                val change = receivedAmount - order.totalAmount
                if (change >= 0) {
                     Text(
                        "Change to Return: ${formatCurrency(change)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(Modifier.height(24.dp))
                
                Button(
                    onClick = {
                        viewModel.processPayment(
                            receivedAmount = receivedAmount,
                            onPaymentComplete = onPaymentComplete
                        )
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = receivedAmount >= order.totalAmount
                ) {
                    Text("Complete Payment", fontSize = 18.sp)
                }
            } else {
                Text("Order not found or could not be loaded.")
            }
        }
    }
}
