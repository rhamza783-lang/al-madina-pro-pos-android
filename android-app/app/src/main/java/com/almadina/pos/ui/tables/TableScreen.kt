package com.almadina.pos.ui.tables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.almadina.pos.model.Table
import com.almadina.pos.model.TableStatus
import com.almadina.pos.ui.theme.ErrorColor
import com.almadina.pos.ui.theme.SuccessColor
import com.almadina.pos.ui.theme.WarningColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableScreen(
    onNavigateToOrder: (tableNumber: Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: TableViewModel = hiltViewModel()
) {
    val tables by viewModel.tables.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select a Table") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tables) { table ->
                TableCard(
                    table = table,
                    onClick = {
                        if (table.status == TableStatus.AVAILABLE) {
                            onNavigateToOrder(table.number)
                        } else {
                            // Logic to view existing order
                            onNavigateToOrder(table.number)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TableCard(table: Table, onClick: () -> Unit) {
    val backgroundColor = when (table.status) {
        TableStatus.AVAILABLE -> SuccessColor.copy(alpha = 0.2f)
        TableStatus.OCCUPIED -> WarningColor.copy(alpha = 0.2f)
        TableStatus.READY_TO_PAY -> ErrorColor.copy(alpha = 0.2f)
    }
    val textColor = when (table.status) {
        TableStatus.AVAILABLE -> SuccessColor
        TableStatus.OCCUPIED -> WarningColor
        TableStatus.READY_TO_PAY -> ErrorColor
    }

    Card(
        modifier = Modifier.aspectRatio(1f).clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = table.number.toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = table.status.displayName,
                    fontSize = 16.sp,
                    color = textColor
                )
            }
        }
    }
}
