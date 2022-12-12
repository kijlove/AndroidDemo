package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgTab1Binding
import com.kijlee.android.demo.entity.ChinaTown
import com.kijlee.android.demo.ui.cityselect.*
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
class FgCitySelect: Fragment() , TabLayout.OnTabSelectedListener, SetCityTabImp {

    var tabList: Array<String>? = null

    var _layoutBind: FgTab1Binding? = null

    var item = ""

    var fragmentList :MutableList<Fragment> = ArrayList()
    var fgProvinceList = FgProvinceList()
    var fgCityList = FgCityList()
    var fgCountyList = FgCountyList()
    var fgTownList = FgTownList()

    private val binding get() = _layoutBind!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tabList = resources.getStringArray(R.array.city_array)

        _layoutBind = FgTab1Binding.inflate(layoutInflater)

        val root: View = binding.root
        val bundle = Bundle()
        bundle.putString(FgTabLayout.Tab_Name,item)
        fgProvinceList.arguments = bundle
        fgProvinceList.setCityTabImp = this
        fgCityList.arguments = bundle
        fgCityList.setCityTabImp = this
        fgCountyList.arguments = bundle
        fgCountyList.setCityTabImp = this
        fgTownList.arguments = bundle
        fgTownList.setCityTabImp = this
        fragmentList.add(fgProvinceList)
        fragmentList.add(fgCityList)
        fragmentList.add(fgCountyList)
        fragmentList.add(fgTownList)


        val vp1Adapter = Vp1Adapter(requireActivity(),fragmentList)
        binding.vp.adapter = vp1Adapter
        var tablayoutMenu  = TabLayoutMediator(binding.tab,binding.vp, {tab,position->
            // 只显示文本
            tab.text = tabList!![position]
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

    // 设置信息
    override fun setTabText(chinaTown: ChinaTown) {
        binding.tab.getTabAt(binding.tab.selectedTabPosition)!!.setText(chinaTown.name)
        when(binding.tab.selectedTabPosition){
            0->{
                fgCityList.cityId = chinaTown.code!!.toLong()
            }
            1->{
                fgCountyList.cityId = chinaTown.city_id
                fgCountyList.countyId = chinaTown.code!!.toLong()
            }
            2->{
                fgTownList.cityId = chinaTown.city_id
                fgTownList.countyId = chinaTown.county_id
                fgTownList.townId = chinaTown.code!!.toLong()
            }
        }
        binding.vp.setCurrentItem(binding.tab.selectedTabPosition+1,true)

    }


}