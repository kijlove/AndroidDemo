package com.kijlee.android.demo.ui.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgTab1Binding
import com.kijlee.android.demo.databinding.FgTabLayoutBinding
import com.kijlee.android.demo.ui.main.FgTabLayout

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.tablayout
 * @ClassName:      FgTab1
 * @Author:     kij
 * @Description:  tab1
 * @Date:    2022/1/19 7:54 下午
 * @Version:    1.0
 */
class FgTab1 : Fragment() {


    var _layoutBind: FgTab1Binding? = null
    var item = ""

    private val binding get() = _layoutBind!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgTab1Binding.inflate(layoutInflater)

        val root: View = binding.root
        binding.name = "切换fragment"

        binding.setOnClickListener {
            when (it.id) {
                R.id.change_fragment->{

                    var bundle = Bundle()
                    bundle.putString(
                        FgTabLayout.Tab_Name,
                        "测试他测试呀"
                    )

                    it.findNavController().navigate(R.id.to_tab_2, bundle)
                }
            }
        }

        return root
    }

}