package com.kijlee.android.demo.ui.compose.ui.nav

import android.os.Build
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kijlee.android.demo.ui.compose.ui.Index
import com.kijlee.android.demo.ui.compose.ui.MainScreen
import com.kijlee.android.demo.ui.compose.ui.SqlLiteIndex
import com.kijlee.android.demo.ui.compose.ui.XzbIndex
import com.kijlee.android.demo.ui.compose.ui.XzbLogon
import com.kijlee.android.demo.ui.compose.ui.XzbHome

/**
 * @ProjectName:    牦牛管家
 * @Package:        com.hyjxpad.tlzs.sczd.ui.nav
 * @ClassName:      NavGraph
 * @Author:     kij
 * @Description:  导航
 * @Date:    2023/12/23 09:33
 * @Version:    1.0
 */

object TouristGuide {
    var navController: NavHostController? = null
        set(value) {
            field = value
        }

    fun toSqlLite() {
        navController?.navigate(RouterPath.SqlLite) {
        }
    }
    fun toIndex() {
        navController?.navigate(RouterPath.Index) {
        }
    }
    fun toXzb() {
        navController?.navigate(RouterPath.Xzb) {
        }
    }
    fun toLogon() {
        navController?.navigate(RouterPath.Logon) {
        }
    }
    fun toXzbHome() {
        navController?.navigate(RouterPath.XzbHome) {
        }
    }
}

private object RouterPath {
    val SqlLite = "sql_lite"
    val Index = "index"
    val Xzb = "xzb"
    val Logon = "Logon"
    val XzbHome = "XzbHome"
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@Composable
fun NavGraph(window: Window) {
    val navHostController = rememberNavController()
    TouristGuide.navController = navHostController

    NavHost(navController = navHostController, startDestination = RouterPath.Index) {

        composable(RouterPath.Index) {
            window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()
//            Index()
            MainScreen()
        }

        composable(RouterPath.SqlLite) {
            SqlLiteIndex()
        }
        composable(RouterPath.Xzb) {
            XzbIndex()
        }
        composable(RouterPath.Logon) {
            XzbLogon()
        }
        composable(RouterPath.XzbHome) {
            XzbHome()
        }
    }
}