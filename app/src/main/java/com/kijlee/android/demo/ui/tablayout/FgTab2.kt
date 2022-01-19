package com.kijlee.android.demo.ui.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FgTab1Binding
import com.kijlee.android.demo.ui.main.FgRetrofit
import com.kijlee.android.demo.ui.main.FgTabLayout

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.tablayout
 * @ClassName:      FgTab2
 * @Author:     kij
 * @Description:  tab2
 * @Date:    2022/1/19 9:37 下午
 * @Version:    1.0
 */
class FgTab2 : Fragment() {


    var _layoutBind: FgTab1Binding? = null
    var item = ""

    private val binding get() = _layoutBind!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgTabLayout.Tab_Name)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString((FgTabLayout.Tab_Name)).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgTab1Binding.inflate(layoutInflater)

        val root: View = binding.root
        binding.name = item

        return root
    }

}