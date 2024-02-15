package com.kijlee.android.demo.ui.compose.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.appbar.AppBarLayout
import com.kijlee.android.demo.ui.compose.ui.theme.ComposeDemoTheme

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      XzbHome
 * @Author:     kij
 * @Description:  薪资宝主页
 * @Date:    2024/1/10 22:17
 * @Version:    1.0
 */


@Composable
fun XzbHome() {
    ScaffoldExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("薪资宝")
                },

                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },

                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ },shape = FloatingActionButtonDefaults.extendedFabShape) {

                Icon(Icons.Default.Add, contentDescription = "Add")
            }

        },

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {


            Column(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFFEA8058)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){

                Text(
                    style = MaterialTheme.typography.displayMedium,
                    text = "0.0",
                    color = Color.White
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "薪资宝账户余额(元)",
                    color = Color.White
                )

            }

            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "账户资金收益情况(元)",
                color = Color(0xFFEA8058)
            )

            Row(horizontalArrangement =  Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)

            ) {

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "昨日收益(元)",
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "0.0",
                )
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(0.5.dp).background(Color.Black))

            Row(horizontalArrangement =  Arrangement.SpaceBetween,modifier =  Modifier.fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
            ) {

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "本月累计收益(元)",
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "0.0",
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(0.5.dp).background(Color.Black))

            Row(horizontalArrangement =  Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
            ) {

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "上月收益(元)",
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "0.0",
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(0.5.dp).background(Color.Black))
            Row(horizontalArrangement =  Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
            ) {

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "账户总收益(元)",
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "0.0",
                )
            }

            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "用户操作情况(元)",
                color = Color(0xFFEA8058)
            )


            Row(horizontalArrangement =  Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
            ) {

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "累计转入(元)",
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "0.0",
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(0.5.dp).background(Color.Black))

            Row(horizontalArrangement =  Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
            ) {

                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "累计转出(元)",
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "0.0",
                )
            }


            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
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
fun showXzbHome(){

    ComposeDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            XzbHome()
        }
    }
}