package com.kijlee.android.demo.ui.compose.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kijlee.android.demo.R
import com.kijlee.android.demo.ui.compose.ui.nav.TouristGuide
import com.kijlee.android.demo.ui.compose.ui.theme.ComposeDemoTheme

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      XzbLogon
 * @Author:     kij
 * @Description:  薪资宝登录
 * @Date:    2024/1/10 21:49
 * @Version:    1.0
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun XzbLogon()  {

    var username by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.mipmap.bg),
            contentDescription = "背景图片",Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Column(modifier = Modifier.align(Alignment.Center)) {

            Image(painter = painterResource(id = R.mipmap.bg_logo),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .padding(bottom = 16.dp),
                contentDescription = "应用logo",)


            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "用户名") },
                placeholder = { Text("请输入用户名") },
                leadingIcon = { Icon(Icons.Filled.AccountBox, null) },
                trailingIcon = { IconButton(onClick = {
                    username = ""
                }){ Icon(Icons.Filled.Cancel, null) }
                },
                modifier = Modifier
                    .height(66.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(6)),
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = userPassword,
                onValueChange = { userPassword = it },
                label = { Text(text = "密码") },
                placeholder = { Text("请输入密码") },
                modifier = Modifier
                    .height(66.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(6)),
                leadingIcon = { Icon(Icons.Filled.Lock, null) },
                trailingIcon = { IconButton(onClick = {
                    userPassword = ""
                }){ Icon(Icons.Filled.Cancel, null) } },
            )


            Spacer(modifier = Modifier.height(8.dp))
            var isCheck = remember { mutableStateOf(true) }

            Row(horizontalArrangement =  Arrangement.End,
                modifier = Modifier.fillMaxWidth()
                    .padding(end = 16.dp),
                verticalAlignment =  Alignment.CenterVertically) {
                Checkbox(checked =  isCheck.value, onCheckedChange = {isCheck.value = it} )
                Text(text = "记住密码",color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }

            Row(horizontalArrangement =  Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
                verticalAlignment =  Alignment.CenterVertically) {
                TextButton(onClick = { /*TODO*/ },) {
                    Text(text = "激活账号" ,color = Color.White, style = MaterialTheme.typography.bodyMedium)
                }
                TextButton(onClick = { TouristGuide.toXzbHome() },) {
                    Text(text = "忘记密码", color = Color.White, style = MaterialTheme.typography.bodyMedium)
                }

            }




            Button(
                onClick = { TouristGuide.toXzbHome() },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "登录",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

        }}

}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun showLogon(){

    ComposeDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            XzbLogon()
        }
    }
}