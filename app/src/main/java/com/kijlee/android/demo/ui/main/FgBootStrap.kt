package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FgBootStrapBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgBootStrap
 * @Author:     kij
 * @Description:  bootstrap分发导航界面
 * @Date:    2022/1/11 10:54 下午
 * @Version:    1.0
 */
class FgBootStrap: Fragment() {


    var _layoutBind: FgBootStrapBinding? = null
    var item = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
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

        _layoutBind = FgBootStrapBinding.inflate(layoutInflater)

        val root: View = binding.root

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }


}