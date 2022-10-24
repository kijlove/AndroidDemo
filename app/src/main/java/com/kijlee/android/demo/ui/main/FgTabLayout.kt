package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FgTabLayoutBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgTabLayout
 * @Author:     kij
 * @Description:  tablayout练习
 * @Date:    2022/1/19 7:20 下午
 * @Version:    1.0
 */
class FgTabLayout  : Fragment() {


    var _layoutBind: FgTabLayoutBinding? = null
    var item = ""
    private val binding get() = _layoutBind!!

    override fun onDestroy() {
        super.onDestroy()
        _layoutBind = null
    }

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

        _layoutBind = FgTabLayoutBinding.inflate(layoutInflater)

        val root: View = binding.root

    return root
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val Tab_Name = "tab_name"
    }

}