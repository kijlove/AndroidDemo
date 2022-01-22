package com.kijlee.android.demo.ui.tablayout

import android.app.Activity
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.os.Parcelable

import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.orhanobut.logger.Logger


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.tablayout
 * @ClassName:      Vp1Adapter
 * @Author:     kij
 * @Description:  tablayout和viewpage适配器
 * @Date:    2022/1/22 12:51 下午
 * @Version:    1.0
 */

class Vp1Adapter constructor(fm: FragmentActivity, data: MutableList<Fragment>) : FragmentStateAdapter(fm) {
    var data: MutableList<Fragment>

    init {
        this.data = data
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return data[position]
    }

}