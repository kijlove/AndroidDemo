package com.kijlee.android.demo.ui.compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      WorkScreen
 * @Author:     kij
 * @Description:
 * @Date:    2025/12/18 15:18
 * @Version:    1.0
 */


@Composable
fun WorkScreen(onTeamClick: () -> Unit) {
    val workItems = listOf(
        MenuItem("Tasks", Icons.Default.List),
        MenuItem("Projects", Icons.Default.Build),
        MenuItem("Reports", Icons.Default.Create),
        MenuItem("Approvals", Icons.Default.Check),
        MenuItem("Meetings", Icons.Default.DateRange),
        MenuItem("Team", Icons.Default.Face),
        MenuItem("Documents", Icons.Default.Edit),
        MenuItem("Performance", Icons.Default.ThumbUp),
        MenuItem("Goals", Icons.Default.Done),
        MenuItem("Feedback", Icons.Default.Share),
        MenuItem("Announcements", Icons.Default.Send)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Work",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(workItems) { item ->
                MenuCard(item, onClick = {
                    if (item.name == "Team") {
                        onTeamClick()
                    }
                })
            }
        }
    }
}
