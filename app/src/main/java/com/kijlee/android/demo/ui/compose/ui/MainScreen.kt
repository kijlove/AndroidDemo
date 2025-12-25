package com.kijlee.android.demo.ui.compose.ui



import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      MainScreen
 * @Author:     kij
 * @Description:
 * @Date:    2025/12/18 15:17
 * @Version:    1.0
 */


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "首页")
    object Contacts : BottomNavItem("contacts", Icons.Default.Phone, "通讯录")
    object Work : BottomNavItem("work", Icons.Default.Build, "工作")
    object Process : BottomNavItem("process", Icons.Default.List, "处理")
    object Mine : BottomNavItem("mine", Icons.Default.Person, "我的")
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    var showTeamScreen by remember { mutableStateOf(false) }

    if (showTeamScreen) {
        TeamScreen(onBack = { showTeamScreen = false })
    } else {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Contacts,
            BottomNavItem.Work,
            BottomNavItem.Process,
            BottomNavItem.Mine
        )

        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selectedTab == item,
                            onClick = { selectedTab = item }
                        )
                    }
                }
            }
        ) { innerPadding ->
            // Apply padding to content to avoid overlap with bottom bar
            androidx.compose.foundation.layout.Box(modifier = Modifier.padding(innerPadding)) {
                when (selectedTab) {
                    BottomNavItem.Home -> HomeScreen()
                    BottomNavItem.Contacts -> ContactsScreen()
                    BottomNavItem.Work -> WorkScreen(onTeamClick = { showTeamScreen = true })
                    BottomNavItem.Process -> ProcessScreen()
                    BottomNavItem.Mine -> MineScreen()
                }
            }
        }
    }
}
