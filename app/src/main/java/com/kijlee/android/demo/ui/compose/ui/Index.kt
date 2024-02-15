package com.kijlee.android.demo.ui.compose.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kijlee.android.demo.R
import com.kijlee.android.demo.ui.compose.ui.nav.TouristGuide
import com.kijlee.android.demo.ui.compose.ui.theme.ComposeDemoTheme

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      Index
 * @Author:     kij
 * @Description:  index
 * @Date:    2024/1/4 23:29
 * @Version:    1.0
 */

@Composable
fun Index() {
    val  modules = stringArrayResource(id = R.array.demo_array)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = StaggeredGridCells.Fixed(3) ,
            content = {
                items(modules.size) { positon ->


                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(8.dp),
                            elevation = CardDefaults.cardElevation(),
                            shape = RoundedCornerShape(size = 12.dp)
                        ) {
                                Box(
                                    modifier = Modifier.clickable {
                                        // 处理点击事件
                                        TouristGuide.toXzb()
                                    }
                                ){
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,

                                        ) {

                                        Image(
                                            painter = painterResource(id = R.drawable.author),
                                            contentDescription = "Avatar icon",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .padding(8.dp),
                                        )
                                        Text(
                                            text = modules[positon],
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.padding( 8.dp)
                                        )
                                    }}

                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

            }
        )


    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun GreetingPreview() {
    ComposeDemoTheme {
        Index()
    }
}
