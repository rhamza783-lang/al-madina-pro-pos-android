package com.almadina.pos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.almadina.pos.ui.auth.LoginScreen
import com.almadina.pos.ui.dashboard.DashboardScreen
import com.almadina.pos.ui.order.OrderScreen
import com.almadina.pos.ui.payment.PaymentScreen
import com.almadina.pos.ui.tables.TableScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToTables = { navController.navigate(Screen.Tables.route) },
                onNavigateToOrders = { /* TODO */ },
                onNavigateToReports = { /* TODO */ },
                onNavigateToInventory = { /* TODO */ },
                onNavigateToCustomers = { /* TODO */ },
                onNavigateToSettings = { /* TODO */ },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screen.Tables.route) {
            TableScreen(
                onNavigateToOrder = { tableNumber ->
                    navController.navigate(Screen.Order.createRoute(tableNumber))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(route = Screen.Order.route) { backStackEntry ->
            val tableNumber = backStackEntry.arguments?.getString("tableNumber")?.toInt() ?: 0
            OrderScreen(
                tableNumber = tableNumber,
                onNavigateToPayment = { orderId ->
                    navController.navigate(Screen.Payment.createRoute(orderId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(route = Screen.Payment.route) { backStackEntry ->
             val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
             PaymentScreen(
                orderId = orderId,
                onPaymentComplete = {
                    navController.navigate(Screen.Dashboard.route) {
                       popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() }
             )
        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object Tables : Screen("tables")
    object Order : Screen("order/{tableNumber}") {
        fun createRoute(tableNumber: Int) = "order/$tableNumber"
    }
    object Payment : Screen("payment/{orderId}") {
        fun createRoute(orderId: String) = "payment/$orderId"
    }
}
