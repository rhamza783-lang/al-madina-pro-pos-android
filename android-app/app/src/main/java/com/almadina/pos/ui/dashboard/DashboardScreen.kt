package com.almadina.pos.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.almadina.pos.model.UserRole
import com.almadina.pos.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToTables: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToReports: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToCustomers: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onLogout: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Al-Madina POS Dashboard") },
                actions = {
                    Text(currentUser?.name ?: "User", modifier = Modifier.padding(end = 8.dp))
                    IconButton(onClick = onLogout) {
                        Icon(Icons.Default.Logout, contentDescription = "Logout")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            // Sales Summary Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SummaryCard(
                    title = "Today's Sales",
                    value = formatCurrency(uiState.todaySales),
                    icon = Icons.Default.AttachMoney,
                    modifier = Modifier.weight(1f)
                )
                SummaryCard(
                    title = "Total Orders",
                    value = uiState.todayOrders.toString(),
                    icon = Icons.Default.Receipt,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            
            // Navigation Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(getDashboardActions(currentUser?.role)) { action ->
                    ActionCard(
                        title = action.title,
                        icon = action.icon,
                        onClick = when(action.title) {
                            "Tables" -> onNavigateToTables
                            "Orders" -> onNavigateToOrders
                            "Reports" -> onNavigateToReports
                            "Inventory" -> onNavigateToInventory
                            "Customers" -> onNavigateToCustomers
                            "Settings" -> onNavigateToSettings
                            else -> {{}}
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SummaryCard(title: String, value: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(title, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ActionCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(modifier = Modifier.aspectRatio(1.2f).clickable(onClick = onClick)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, style = MaterialTheme.typography.titleMedium)
        }
    }
}

data class DashboardAction(val title: String, val icon: ImageVector, val requiredRole: UserRole?)

fun getDashboardActions(userRole: UserRole?): List<DashboardAction> {
    val allActions = listOf(
        DashboardAction("Tables", Icons.Default.TableRestaurant, UserRole.WAITER),
        DashboardAction("Orders", Icons.Default.Receipt, UserRole.WAITER),
        DashboardAction("Reports", Icons.Default.BarChart, UserRole.MANAGER),
        DashboardAction("Inventory", Icons.Default.Inventory, UserRole.MANAGER),
        DashboardAction("Customers", Icons.Default.People, UserRole.CASHIER),
        DashboardAction("Settings", Icons.Default.Settings, UserRole.ADMIN)
    )
    // This is a simplified permission model. 
    // A real app might have more granular permissions.
    return allActions.filter {
        userRole != null
    }
}

fun formatCurrency(amount: Double): String {
    return NumberFormat.getCurrencyInstance(Locale("en", "PK")).format(amount)
}
