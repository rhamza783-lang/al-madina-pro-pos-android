package com.almadina.pos.repository

import com.almadina.pos.data.local.TableDao
import com.almadina.pos.model.Table
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableRepository @Inject constructor(private val tableDao: TableDao) {
    // This now fetches REAL data from the local database.
    fun getAllTables(): Flow<List<Table>> {
        return tableDao.getAllTables()
    }
}
