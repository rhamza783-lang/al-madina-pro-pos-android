package com.almadina.pos.ui.tables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almadina.pos.model.Table
import com.almadina.pos.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableRepository: TableRepository
) : ViewModel() {

    val tables: StateFlow<List<Table>> = tableRepository.getAllTables()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
