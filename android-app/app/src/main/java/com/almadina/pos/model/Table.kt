package com.almadina.pos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "tables")
data class Table(
    @PrimaryKey val id: String,
    val number: Int,
    val section: String,
    val capacity: Int,
    var status: TableStatus = TableStatus.AVAILABLE
) : Parcelable

enum class TableStatus(val displayName: String) {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    READY_TO_PAY("Bill Ready")
}
