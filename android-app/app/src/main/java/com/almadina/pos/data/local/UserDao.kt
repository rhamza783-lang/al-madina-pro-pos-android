package com.almadina.pos.data.local

import androidx.room.*
import com.almadina.pos.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE staffId = :staffId")
    suspend fun getUserByStaffId(staffId: String): User?
}
