package com.kijlee.android.demo.ui.recycler

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.recycler
 * @ClassName:      DecorationCallback
 * @Author:     kij
 * @Description:  设置分组回调
 * @Date:    2023/7/14 10:31
 * @Version:    1.0
 */
interface DecorationCallback {

    fun getGroupId(position: Int): Long
    fun getGroupFirstLine(position: Int): String
}