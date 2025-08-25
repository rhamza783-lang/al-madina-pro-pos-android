package com.almadina.pos.ui.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.almadina.pos.model.Item
import com.almadina.pos.model.OrderItem
import com.almadina.pos.ui.dashboard.formatCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    tableNumber: Int,
    onNavigateToPayment: (orderId: String) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val order by viewModel.currentOrder.collectAsState()
    val menuItems by viewModel.menuItems.collectAsState()

    LaunchedEffect(tableNumber) {
        viewModel.loadOrCreateOrder(tableNumber)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Table $tableNumber - Order #${order?.invoiceNumber.orEmpty()}") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Row(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            // Left Panel: Menu Items
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.weight(1.5f).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(menuItems) { item ->
                    MenuItemCard(item = item, onClick = { viewModel.addItemToOrder(item) })
                }
            }

            // Right Panel: Order Cart
            Card(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        "Current Order",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                    Divider()

                    if (order?.items.isNullOrEmpty()) {
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Text("No items added yet.")
                        }
                    } else {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(order!!.items) { orderItem ->
                                CartItemRow(orderItem = orderItem)
                                Divider()
                            }
                        }
                    }

                    // Order Summary & Actions
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total:", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text(formatCurrency(order?.totalAmount ?: 0.0), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { order?.id?.let { onNavigateToPayment(it) } },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            enabled = !order?.items.isNullOrEmpty()
                        ) {
                            Text("Proceed to Payment", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(item: Item, onClick: () -> Unit) {
    Card(modifier = Modifier.aspectRatio(1.2f).clickable(onClick = onClick)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(item.name, style = MaterialTheme.typography.titleMedium, maxLines = 2)
            Text(formatCurrency(item.price), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CartItemRow(orderItem: OrderItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(orderItem.itemName, fontWeight = FontWeight.Bold)
            Text("${orderItem.quantity} x ${formatCurrency(orderItem.unitPrice)}")
        }
        Text(formatCurrency(orderItem.totalPrice), fontWeight = FontWeight.SemiBold)
    }
}
