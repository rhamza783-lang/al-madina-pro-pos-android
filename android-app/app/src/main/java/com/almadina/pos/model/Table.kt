package com.almadina.pos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.almadina.pos.data.local.Converters

@Entity(tableName = "tables")
@TypeConverters(Converters::class)
data class Table(
    @PrimaryKey
    val id: String,
    val number: Int,
    val section: String,
    val capacity: Int,
    var status: TableStatus = TableStatus.AVAILABLE
)

enum class TableStatus(val displayName: String) {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    READY_TO_PAY("Bill Ready")
}
