package com.kijlee.android.demo.ui.compose.ui

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      ProcessScreen
 * @Author:     kij
 * @Description:
 * @Date:    2025/12/18 15:18
 * @Version:    1.0
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kijlee.android.demo.ui.compose.ui.theme.LoginAppTheme

@Composable
fun ProcessScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("My Applications", "Approval", "My Messages")

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Process",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ProcessList(listOf("Leave Application - Pending", "Expense Report - Approved", "Travel Request - Pending"))
            1 -> ProcessList(listOf("Team Leave Request - John", "Purchase Order #123", "New Hire Approval"))
            2 -> ProcessList(listOf("System Maintenance", "Policy Update", "Holiday Announcement"))
        }
    }
}

@Composable
fun ProcessList(items: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProcessScreenPreview() {
    LoginAppTheme {
        ProcessScreen()
    }
}