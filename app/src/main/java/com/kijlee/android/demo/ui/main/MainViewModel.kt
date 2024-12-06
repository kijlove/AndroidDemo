package com.kijlee.android.demo.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kijlee.android.demo.R

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      MainViewModle
 * @Author:     kij
 * @Description:  主页viewmodle
 * @Date:    2024/4/27 18:21
 * @Version:    1.0
 *
 * 不能传入context
 */
class MainViewModel(application: Application):ViewModel() {
    fun getMainMenuList(application: Application){
        val resource = application.resources.getStringArray(R.array.demo_array).toMutableList()


    }


}