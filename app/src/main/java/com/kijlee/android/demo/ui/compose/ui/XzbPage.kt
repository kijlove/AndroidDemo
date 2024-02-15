package com.kijlee.android.demo.ui.compose.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kijlee.android.demo.R
import com.kijlee.android.demo.ui.compose.ui.nav.TouristGuide
import com.kijlee.android.demo.ui.compose.ui.theme.ComposeDemoTheme
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      XzbPage
 * @Author:     kij
 * @Description:  薪资宝界面测试
 * @Date:    2024/1/10 13:26
 * @Version:    1.0
 */



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun XzbIndex() {
    var visibleText by remember { mutableStateOf(true) }

    val welcomeTags :MutableList<WelcomeTag> = ArrayList()
    welcomeTags.add(WelcomeTag(R.mipmap.xzb_first,"理财产品哪家强","适合自己才是王道",Color(0xC2678FB4),Color(0XFF678FB4)))
    welcomeTags.add(WelcomeTag(R.mipmap.xzb_second,"工资“新”玩法","彻底脱离月光族",Color(0xB565B0B4),Color(0XFF65B0B4)))
    welcomeTags.add(WelcomeTag(R.mipmap.xzb_thirdly,"随时随地,心中有数","收益高，安全有保障",Color(0xD59B90BC),Color(0XFF9B90BC)))
    welcomeTags.add(WelcomeTag(R.mipmap.xzb_thirdly,"薪资宝","更懂得理财",Color(0xD59B90BC),Color(0XFF9B90BC)))

    Box(modifier = Modifier.fillMaxSize()){

        val pagerState = rememberPagerState(pageCount = { welcomeTags.size })

        LaunchedEffect(pagerState) {
            // Collect from the a snapshotFlow reading the currentPage
            snapshotFlow { pagerState.currentPage }.collect { page ->
                // Do something with each page change, for example:
                // viewModel.sendPageSelectedEvent(page)
                visibleText = page == 3
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { content ->
            // Our page content
            WelcomePage(modifier = Modifier.fillMaxSize().align(Alignment.Center), welcomeTag = welcomeTags[content])
        }

        WelcomeCycle(modifier = Modifier.wrapContentHeight().fillMaxWidth().align(Alignment.BottomCenter),
            welcomeTags[pagerState.currentPage].bgColor,pagerState,visibleText)

    }
}

/**
 * 首页轮换界面布局中用到的控件
 */
@Composable
fun WelcomePage(modifier: Modifier,welcomeTag:WelcomeTag){

    Column(modifier = modifier.background(welcomeTag.bgColor)) {

        Image(painter = painterResource(id = welcomeTag.bgImageRes), contentDescription = "${welcomeTag.title}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 58.dp, bottom = 20.dp)
                .clip(CircleShape)
                .background(welcomeTag.sbgColor)
                .border(border = BorderStroke(2.dp, Color.Gray), shape = CircleShape)
        )
        Text(text = welcomeTag.title, style = MaterialTheme.typography.titleLarge,modifier = Modifier
            .align(Alignment.CenterHorizontally), color = Color.White)
        Text(text = welcomeTag.subTitle, style = MaterialTheme.typography.bodySmall,modifier = Modifier
            .align(Alignment.CenterHorizontally), color = Color.White
        )
    }



}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeCycle(modifier: Modifier,selectColor:Color,pagerState: PagerState,visible:Boolean){

    Column(modifier = modifier) {
        Row(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = if (visible) 26.dp else 76.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) selectColor else Color.LightGray
                Box(modifier = Modifier.padding(12.dp).clip(CircleShape).background(color).size(20.dp))
            }
        }

        if(visible){
            Button(onClick = { TouristGuide.toLogon()},modifier = Modifier
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
                ,
                shape = ButtonDefaults.outlinedShape

            ) {
                Text(text = "开始使用",)
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
fun showXzb(){

    ComposeDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            XzbIndex()
        }
    }
}


data class WelcomeTag(val bgImageRes:Int, val title:String, val subTitle:String,val bgColor:Color,val sbgColor:Color)