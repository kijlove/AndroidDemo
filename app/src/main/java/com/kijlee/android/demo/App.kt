package com.kijlee.android.demo

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDexApplication
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.kijlee.android.demo.net.Api.Companion.Luffy_City
import com.kijlee.android.demo.net.Api.Companion.APP_Luffy_City
import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo
 * @ClassName:      App
 * @Author:     kij
 * @Description:  Application
 * @Date:    2022/1/10 9:21 上午
 * @Version:    1.0
 */
class App: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        // setup default typefaces 字体
        TypefaceProvider.registerDefaultIconSets()
        RetrofitUrlManager.getInstance().setDebug(true)
        //将每个 BaseUrl 进行初始化,运行时可以随时改变 DOMAIN_NAME 对应的值,从而达到切换 BaseUrl 的效果
        RetrofitUrlManager.getInstance().putDomain(Luffy_City, APP_Luffy_City)

    }
}