package com.kijlee.android.demo.ui.bootstrap

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand
import com.kijlee.android.demo.R

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      CustomBootstrapStyle
 * @Author:     kij
 * @Description:  A custom Bootstrap Style. Implement {@see BootstrapBrand} in your own classes to define styles.
 * @Date:    2022/1/12 8:58 下午
 * @Version:    1.0
 */

class CustomBootstrapStyle(context: Context) : BootstrapBrand {
    @ColorInt
    private val defaultFill: Int

    @ColorInt
    private val defaultEdge: Int

    @ColorInt
    private val defaultTextColor: Int

    @ColorInt
    private val activeFill: Int

    @ColorInt
    private val activeEdge: Int

    @ColorInt
    private val activeTextColor: Int

    @ColorInt
    private val disabledFill: Int

    @ColorInt
    private val disabledEdge: Int

    @ColorInt
    private val disabledTextColor: Int
    override fun defaultFill(context: Context): Int {
        return defaultFill
    }

    override fun defaultEdge(context: Context): Int {
        return defaultEdge
    }

    override fun defaultTextColor(context: Context): Int {
        return defaultTextColor
    }

    override fun activeFill(context: Context): Int {
        return activeFill
    }

    override fun activeEdge(context: Context): Int {
        return activeEdge
    }

    override fun activeTextColor(context: Context): Int {
        return activeTextColor
    }

    override fun disabledFill(context: Context): Int {
        return disabledFill
    }

    override fun disabledEdge(context: Context): Int {
        return disabledEdge
    }

    override fun disabledTextColor(context: Context): Int {
        return disabledTextColor
    }

    override fun getColor(): Int {
        return defaultFill
    }

    init {

        defaultFill = ContextCompat.getColor(context,R.color.custom_default_fill)
        defaultEdge = ContextCompat.getColor(context,R.color.custom_default_edge)
        defaultTextColor = ContextCompat.getColor(context,R.color.white)
        activeFill = ContextCompat.getColor(context,R.color.custom_active_fill)
        activeEdge = ContextCompat.getColor(context,R.color.custom_active_edge)
        activeTextColor = ContextCompat.getColor(context,R.color.black)
        disabledFill = ContextCompat.getColor(context,R.color.custom_disabled_fill)
        disabledEdge = ContextCompat.getColor(context,R.color.custom_disabled_edge)
        disabledTextColor = ContextCompat.getColor(context,R.color.bootstrap_gray)
    }
}