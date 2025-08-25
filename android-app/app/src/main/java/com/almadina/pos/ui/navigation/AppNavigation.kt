package com.almadina.pos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.almadina.pos.ui.analytics.AnalyticsDashboard
import com.almadina.pos.ui.auth.LoginScreen
import com.almadina.pos.ui.dashboard.DashboardScreen
import com.almadina.pos.ui.inventory.AdvancedInventoryScreen
import com.almadina.pos.ui.order.OrderScreen
import com.almadina.pos.ui.payment.PaymentScreen
import com.almadina.pos.ui.tables.TableScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Dashboard.route) { popUpTo(Screen.Login.route) { inclusive = true } }
            })
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToTables = { navController.navigate(Screen.Tables.route) },
                onNavigateToReports = { navController.navigate(Screen.Analytics.route) },
                onNavigateToInventory = { navController.navigate(Screen.Inventory.route) },
                onLogout = {
                    navController.navigate(Screen.Login.route) { popUpTo(Screen.Dashboard.route) { inclusive = true } }
                },
                // Add other navigation actions here
                onNavigateToOrders = {}, 
                onNavigateToCustomers = {},
                onNavigateToSettings = {}
            )
        }
        composable(Screen.Tables.route) {
            TableScreen(
                onNavigateToOrder = { tableNum -> navController.navigate(Screen.Order.createRoute(tableNum)) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Order.route) { backStack ->
            val tableNumber = backStack.arguments?.getString("tableNumber")?.toInt() ?: 0
            OrderScreen(
                tableNumber = tableNumber,
                onNavigateToPayment = { orderId -> navController.navigate(Screen.Payment.createRoute(orderId)) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Payment.route) { backStack ->
             val orderId = backStack.arguments?.getString("orderId") ?: ""
             PaymentScreen(
                orderId = orderId,
                onPaymentComplete = { navController.popBackStack(Screen.Tables.route, false) },
                onNavigateBack = { navController.popBackStack() }
             )
        }
        composable(Screen.Analytics.route) {
            AnalyticsDashboard(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Inventory.route) {
            AdvancedInventoryScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object Tables : Screen("tables")
    object Analytics : Screen("analytics")
    object Inventory : Screen("inventory")
    object Order : Screen("order/{tableNumber}") {
        fun createRoute(tableNumber: Int) = "order/$tableNumber"
    }
    object Payment : Screen("payment/{orderId}") {
        fun createRoute(orderId: String) = "payment/$orderId"
    }
}
