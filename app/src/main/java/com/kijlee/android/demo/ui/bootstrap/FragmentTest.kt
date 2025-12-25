package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.font.FontAwesome
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleAwesomeTextViewBinding
import com.kijlee.android.demo.databinding.FragmentTestBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FragmentTest
 * @Author:     kij
 * @Description:  测试
 * @Date:    2024/12/19 10:59
 * @Version:    1.0
 */
class FragmentTest: Fragment() {
    var _layoutBind: FragmentTestBinding? = null
    private val binding get() = _layoutBind!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _layoutBind = FragmentTestBinding.inflate(layoutInflater)
        val root: View = binding.root

        return root
    }

}