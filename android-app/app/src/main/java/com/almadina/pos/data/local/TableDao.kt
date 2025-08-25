package com.almadina.pos.data.local

import androidx.room.Dao
import androidx.room.Query
import com.almadina.pos.model.Table
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {
    @Query("SELECT * FROM tables ORDER BY number ASC")
    fun getAllTables(): Flow<List<Table>>
}
