package com.kijlee.android.demo.ui.compose.ui


import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginapp.ui.home.HomeLeafDispatcher
import com.kijlee.android.demo.ui.compose.model.Module
import com.kijlee.android.demo.ui.compose.ui.home.HomeScreen
import com.kijlee.android.demo.ui.compose.ui.theme.ComposeDemoTheme
import com.kijlee.android.demo.ui.compose.ui.work.TeamScreen
import com.kijlee.android.demo.ui.compose.ui.work.WorkLeafDispatcher
import com.kijlee.android.demo.ui.compose.ui.work.WorkScreen
import com.orhanobut.logger.Logger

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    var showTeamScreen by remember { mutableStateOf(false) }

    // Home Navigation Stack
    var homeNavStack by remember { mutableStateOf(listOf<Module>()) }

    // Work Navigation Stack
    var workNavStack by remember { mutableStateOf(listOf<Module>()) }

    // Parse Home Modules
    val homeModules = remember {
        val jsonString = """
            [
                { "button": "1", "name": "实物资产", "mid": "bc", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "财会", "mid": "bb", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "车务", "mid": "bca", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "文件与资料", "mid": "bd", "pid": "0", "userId": 10379 },
                { "button": "0", "name": "职工", "mid": "be", "pid": "0", "userId": 10379 },
                { "button": "0", "name": "客户", "mid": "bf", "pid": "0", "userId": 10379 },
                { "button": "0", "name": "财务", "mid": "bg", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "订单", "mid": "bh", "pid": "0", "userId": 10379 },
                { "button": "0", "name": "权限", "mid": "bi", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "投诉", "mid": "bj", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "商品", "mid": "bk", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "销售统计", "mid": "bl", "pid": "0", "userId": 10379 },
                { "button": "0", "name": "仓库", "mid": "bm", "pid": "0", "userId": 10379 },
                { "button": "1", "name": "登录记录", "mid": "bea", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "职工档案", "mid": "beb", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "考勤", "mid": "bec", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "请假动态", "mid": "bed", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "加班动态", "mid": "bee", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "工资", "mid": "bef", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "工作记录", "mid": "beg", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "近7日打卡记录", "mid": "beh", "pid": "be", "userId": 10379 },
                { "button": "1", "name": "客户信息", "mid": "bfa", "pid": "bf", "userId": 10379 },
                { "button": "1", "name": "订购信息", "mid": "bfb", "pid": "bf", "userId": 10379 },
                { "button": "1", "name": "账务信息", "mid": "bfc", "pid": "bf", "userId": 10379 },
                { "button": "1", "name": "资金", "mid": "bga", "pid": "bg", "userId": 10379 },
                { "button": "1", "name": "票据", "mid": "bgb", "pid": "bg", "userId": 10379 },
                { "button": "1", "name": "审批权限", "mid": "bia", "pid": "bi", "userId": 10379 },
                { "button": "1", "name": "原辅材料库", "mid": "bma", "pid": "bm", "userId": 10379 },
                { "button": "1", "name": "成品库", "mid": "bmb", "pid": "bm", "userId": 10379 }
            ]
        """
        try {
            val jsonArray = org.json.JSONArray(jsonString)
            val list = mutableListOf<Module>()
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                list.add(
                    Module(
                        mid = item.getString("mid"),
                        pid = item.getString("pid"),
                        name = item.getString("name"),
                        userId = item.getInt("userId"),
                        button = item.optString("button")
                    )
                )
            }
            list
        } catch (e: Exception) {
            emptyList<Module>()
        }
    }

    // Parse Work Modules
    val workModules = remember {
        val jsonString = """
            [
                { "userId": 10379, "mid": "ba", "pid": "0", "oid": 4884, "name": "公司总览" },
                { "userId": 10379, "mid": "ca", "pid": "0", "oid": 4884, "name": "手机端工作" },
                { "userId": 10379, "mid": "ea", "pid": "0", "oid": 4884, "name": "权限管理" },
                { "userId": 10379, "mid": "ia", "pid": "0", "oid": 4884, "name": "启用管理" },
                { "userId": 10379, "mid": "fa", "pid": "0", "oid": 4884, "name": "草稿编辑器" },
                { "userId": 10379, "mid": "ja", "pid": "0", "oid": 4884, "name": "备忘与日程" },
                { "userId": 10379, "mid": "km", "pid": "0", "oid": 4884, "name": "工作记录点评" },
                { "userId": 10379, "mid": "mc", "pid": "0", "oid": 4884, "name": "中枢管控" },
                { "userId": 10379, "mid": "md", "pid": "0", "oid": 4884, "name": "冻结账号" },
                { "userId": 10379, "mid": "ma", "pid": "0", "oid": 4884, "name": "个人中心" },
                { "userId": 10379, "mid": "na", "pid": "0", "oid": 4884, "name": "参考资料" },
                { "userId": 10379, "mid": "db", "pid": "0", "oid": 4884, "name": "日常事务" },
                { "userId": 10379, "mid": "dj", "pid": "0", "oid": 4884, "name": "跨机构访问" },
                { "userId": 10379, "mid": "cb", "pid": "ca", "oid": 4884, "name": "浏览管理" },
                { "userId": 10379, "mid": "ce", "pid": "ca", "oid": 4884, "name": "会议管理" },
                { "userId": 10379, "mid": "cg", "pid": "ca", "oid": 4884, "name": "资源管理" },
                { "userId": 10379, "mid": "cm", "pid": "ca", "oid": 4884, "name": "考核成绩公示板" },
                { "userId": 10379, "mid": "cn", "pid": "ca", "oid": 4884, "name": "公共信息管理" },
                { "userId": 10379, "mid": "co", "pid": "ca", "oid": 4884, "name": "公共信息" },
                { "userId": 10379, "mid": "cw", "pid": "ca", "oid": 4884, "name": "考勤打卡" },
                { "userId": 10379, "mid": "cz", "pid": "ca", "oid": 4884, "name": "展示管理" },
                { "userId": 10379, "mid": "ed", "pid": "ea", "oid": 4884, "name": "当前权限" },
                { "userId": 10379, "mid": "ee", "pid": "ea", "oid": 4884, "name": "审批查看" },
                { "userId": 10379, "mid": "eg", "pid": "ea", "oid": 4884, "name": "修改记录" },
                { "userId": 10379, "mid": "jc", "pid": "ja", "oid": 4884, "name": "备忘与日程" },
                { "userId": 10379, "mid": "mf", "pid": "ma", "oid": 4884, "name": "系统设置" },
                { "userId": 10379, "mid": "mi", "pid": "ma", "oid": 4884, "name": "登录设置" },
                { "userId": 10379, "mid": "nb", "pid": "na", "oid": 4884, "name": "类别设置" },
                { "userId": 10379, "mid": "nc", "pid": "na", "oid": 4884, "name": "文件管理" }
            ]
        """
        try {
            val jsonArray = org.json.JSONArray(jsonString)
            val list = mutableListOf<Module>()
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                list.add(
                    Module(
                        mid = item.getString("mid"),
                        pid = item.getString("pid"),
                        name = item.getString("name"),
                        userId = item.getInt("userId"),
                        button = item.optString("button")
                    )
                )
            }
            list
        } catch (e: Exception) {
            emptyList<Module>()
        }
    }

    if (homeNavStack.isNotEmpty()) {
        val currentModule = homeNavStack.last()
        // Check if current module has children (based on original list)
        val hasChildren = homeModules.any { it.pid == currentModule.mid }

        if (hasChildren) {
            com.example.loginapp.ui.home.SubModuleScreen(
                currentModule = currentModule,
                allModules = homeModules,
                onItemClick = { module ->
                    homeNavStack = homeNavStack + module
                },
                onBack = {
                    homeNavStack = homeNavStack.dropLast(1)
                }
            )
        } else {
            // It's a leaf node -> Use Dispatcher
            com.example.loginapp.ui.home.HomeLeafDispatcher(
                module = currentModule,
                onBack = {
                    homeNavStack = homeNavStack.dropLast(1)
                }
            )
        }
    } else if (workNavStack.isNotEmpty()) {
        val currentModule = workNavStack.last()
        val hasChildren = workModules.any { it.pid == currentModule.mid }

        if (hasChildren) {
            com.example.loginapp.ui.home.SubModuleScreen(
                currentModule = currentModule,
                allModules = workModules,
                onItemClick = { module ->
                    workNavStack = workNavStack + module
                },
                onBack = {
                    workNavStack = workNavStack.dropLast(1)
                }
            )
        } else {
            // Leaf node -> Use Work Dispatcher
            WorkLeafDispatcher(
                module = currentModule,
                onBack = {
                    workNavStack = workNavStack.dropLast(1)
                }
            )
        }
    } else if (showTeamScreen) {
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
                            icon = {
                                if (item == BottomNavItem.Process) {
                                    BadgedBox(
                                        badge = {
                                            Badge {
                                                Text(text = "8")
                                            }
                                        }
                                    ) {
                                        Icon(item.icon, contentDescription = item.label)
                                    }
                                } else {
                                    Icon(item.icon, contentDescription = item.label)
                                }
                            },
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
                    BottomNavItem.Home -> HomeScreen(
                        modules = homeModules,
                        onModuleClick = { module ->
                            homeNavStack = homeNavStack + module
                        }
                    )
                    BottomNavItem.Contacts -> ContactsScreen()
                    BottomNavItem.Work -> WorkScreen(
                        modules = workModules,
                        onModuleClick = { module ->
                            workNavStack = workNavStack + module
                        }
                    )
                    BottomNavItem.Process -> ProcessScreen()
                    BottomNavItem.Mine -> MineScreen()
                }
            }
        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun showMainScreen(){

    ComposeDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            MainScreen()
        }
    }
}