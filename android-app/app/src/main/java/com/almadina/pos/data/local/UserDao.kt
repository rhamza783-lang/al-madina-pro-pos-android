package com.almadina.pos.data.local

import androidx.room.Dao
import androidx.room.Query
import com.almadina.pos.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE staffId = :staffId LIMIT 1")
    suspend fun getUserByStaffId(staffId: String): User?
}
