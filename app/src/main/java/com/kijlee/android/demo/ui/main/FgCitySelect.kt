package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgTab1Binding
import com.kijlee.android.demo.ui.cityselect.FgCityList
import com.kijlee.android.demo.ui.tablayout.FgTab1List
import com.kijlee.android.demo.ui.tablayout.Vp1Adapter
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgCitySelect
 * @Author:     kij
 * @Description:  城市选择
 * @Date:    2022/10/18 13:11
 * @Version:    1.0
 */
class FgCitySelect: Fragment() , TabLayout.OnTabSelectedListener{

    var tabList: Array<String>? = null

    var _layoutBind: FgTab1Binding? = null

    var item = ""

    var fragmentList :MutableList<Fragment> = ArrayList()

    private val binding get() = _layoutBind!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tabList = resources.getStringArray(R.array.city_array)

        _layoutBind = FgTab1Binding.inflate(layoutInflater)

        val root: View = binding.root
        for(item in tabList!!){
            var fragment = FgCityList()
            val bundle = Bundle()
            bundle.putString(FgTabLayout.Tab_Name,item)
            fragment.arguments = bundle
            fragmentList.add(fragment)
        }
        val vp1Adapter = Vp1Adapter(requireActivity(),fragmentList)
        binding.vp.adapter = vp1Adapter
        var tablayoutMenu  = TabLayoutMediator(binding.tab,binding.vp, {tab,position->
            // 只显示文本
            tab.text = tabList!![position]
            // 显示自定义view控件
            val view = View.inflate(requireContext(), R.layout.layout_tab_title_view,null) as ConstraintLayout
            view.findViewById<TextView>(R.id.tab_name).setText(tabList!![position])
            tab.customView = view
        })
        tablayoutMenu.attach()
        binding.tab.addOnTabSelectedListener(this)
        binding.setOnClickListener {
            when (it.id) {
            }
        }

        return root
    }
    override fun onTabSelected(tab: TabLayout.Tab?) {
//        切换 fragment
        Logger.e("onTabSelected-----${tab!!.text}")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        Logger.e("onTabUnselected-----${tab!!.text}")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        Logger.e("onTabReselected-----${tab!!.text}")
    }


}