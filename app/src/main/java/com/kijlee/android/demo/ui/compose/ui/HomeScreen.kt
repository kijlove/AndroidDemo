package com.kijlee.android.demo.ui.compose.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      HomeScreen
 * @Author:     kij
 * @Description:
 * @Date:    2025/12/18 15:17
 * @Version:    1.0
 */

data class MenuItem(val name: String, val icon: ImageVector)

@Composable
fun HomeScreen() {
    val menuItems = listOf(
        MenuItem("Attendance", Icons.Default.DateRange),
        MenuItem("Email", Icons.Default.Email),
        MenuItem("Calendar", Icons.Default.DateRange),
        MenuItem("Reports", Icons.Default.Info),
        MenuItem("Settings", Icons.Default.Settings),
        MenuItem("Notifications", Icons.Default.Notifications),
        MenuItem("Tasks", Icons.Default.List),
        MenuItem("Projects", Icons.Default.AccountBox),
        MenuItem("Favorites", Icons.Default.Favorite),
        MenuItem("Shop", Icons.Default.ShoppingCart),
        MenuItem("Support", Icons.Default.Call),
        MenuItem("More", Icons.Default.Add)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(menuItems) { item ->
                MenuCard(item){}
            }
        }
    }
}

@Composable
fun MenuCard(item: MenuItem,onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize().clickable {
                        onClick()
                    }
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.name,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )

            }
    }
}
