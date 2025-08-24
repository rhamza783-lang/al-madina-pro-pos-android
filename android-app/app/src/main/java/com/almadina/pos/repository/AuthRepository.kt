package com.almadina.pos.repository

import com.almadina.pos.model.User
import com.almadina.pos.model.UserRole
import javax.inject.Inject
import javax.inject.Singleton

// This is a simplified repository. In a real app, it would use an ApiService.
@Singleton
class AuthRepository @Inject constructor() {

    private var currentUser: User? = null

    suspend fun login(staffId: String, pin: String): Result<User> {
        // Mock login logic. In a real app, this would be an API call.
        return if (staffId.isNotEmpty() && pin == "1234") {
            val user = User(
                id = "user-${staffId}",
                staffId = staffId,
                name = "Mock Staff",
                role = UserRole.WAITER,
                isActive = true
            )
            currentUser = user
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
    
    fun getCurrentUser(): User? {
        return currentUser
    }

    fun logout() {
        currentUser = null
    }
}
