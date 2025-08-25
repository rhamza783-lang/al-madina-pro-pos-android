package com.almadina.pos.repository

import com.almadina.pos.model.Table
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableRepository @Inject constructor() {

    // In a real app, this data would come from a TableDao or an ApiService
    private val mockTables = (1..16).map {
        Table(
            id = UUID.randomUUID().toString(),
            number = it,
            section = "Main Hall",
            capacity = 4
        )
    }

    fun getAllTables(): Flow<List<Table>> {
        // Return a flow of the mock list
        return flowOf(mockTables)
    }
}
