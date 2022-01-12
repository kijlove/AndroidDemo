package com.kijlee.android.demo

import android.app.Application
import android.util.Log
import com.beardedhen.androidbootstrap.TypefaceProvider

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo
 * @ClassName:      App
 * @Author:     kij
 * @Description:  Application
 * @Date:    2022/1/10 9:21 上午
 * @Version:    1.0
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        // setup default typefaces
        TypefaceProvider.registerDefaultIconSets()
    }
}