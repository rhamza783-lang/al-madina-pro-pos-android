fun getDashboardActions(userRole: UserRole?): List<DashboardAction> {
    val allActions = listOf(
        DashboardAction("Tables", Icons.Default.TableRestaurant, UserRole.WAITER),
        DashboardAction("Reports", Icons.Default.BarChart, UserRole.MANAGER),
        DashboardAction("Inventory", Icons.Default.Inventory, UserRole.MANAGER),
        DashboardAction("Settings", Icons.Default.Settings, UserRole.ADMIN)
    )
    // A real app should have a more granular, role-based permission system.
    // This simplified logic just shows actions if a user is logged in.
    return if (userRole != null) allActions else emptyList()
}
