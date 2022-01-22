package com.kijlee.android.demo.ui.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgTab1Binding
import com.kijlee.android.demo.databinding.FgTabLayoutBinding
import com.kijlee.android.demo.ui.main.FgTabLayout
import com.kijlee.android.demo.ui.main.FgTabLayout.Companion.Tab_Name
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.tablayout
 * @ClassName:      FgTab1
 * @Author:     kij
 * @Description:  tab1
 * @Date:    2022/1/19 7:54 下午
 * @Version:    1.0
 */
class FgTab1 : Fragment() , TabLayout.OnTabSelectedListener{

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
        tabList = resources.getStringArray(R.array.demo_array)

        _layoutBind = FgTab1Binding.inflate(layoutInflater)

        val root: View = binding.root
        for(item in tabList!!){
            var tab = binding.tab.newTab()

            tab.text = item
            binding.tab.addTab(tab)
            var fragment = FgTab1List()
            val bundle = Bundle()
            bundle.putString(Tab_Name,item)
            fragment.arguments = bundle
            fragmentList.add(fragment)
        }
        val vp1Adapter = Vp1Adapter(requireActivity(),fragmentList)
        binding.vp.adapter = vp1Adapter
        var tablayoutMenu  = TabLayoutMediator(binding.tab,binding.vp, {tab,position->
            Logger.e("tab.text-----${tab.text}position----${position}")
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


}