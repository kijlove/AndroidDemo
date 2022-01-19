package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgTabLayoutBinding
import com.kijlee.android.demo.ui.tablayout.FgTab1
import androidx.navigation.findNavController
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgTabLayout
 * @Author:     kij
 * @Description:  tablayout练习
 * @Date:    2022/1/19 7:20 下午
 * @Version:    1.0
 */
class FgTabLayout  : Fragment(), TabLayout.OnTabSelectedListener {


    var _layoutBind: FgTabLayoutBinding? = null
    var item = ""
    var tabList: MutableList<String> = ArrayList()
    var fragmentList :MutableList<Fragment> = ArrayList()
    private val binding get() = _layoutBind!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgMain.Item_Id)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgMain.Item_Id).toString()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tabList = resources.getStringArray(R.array.demo_array).toMutableList()

        _layoutBind = FgTabLayoutBinding.inflate(layoutInflater)

        val root: View = binding.root
        for(item in tabList){
            var tab = binding.tab.newTab()

            tab.text = item
            binding.tab.addTab(tab)
            var fragment = FgTab1()
            fragmentList.add(fragment)
        }
        binding.tab.addOnTabSelectedListener(this)

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

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val Tab_Name = "tab_name"
    }

}