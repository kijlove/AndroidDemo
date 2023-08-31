package com.kijlee.android.demo.ui.fragmentsave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgTab1Binding
import com.kijlee.android.demo.databinding.FragmentSaveBBinding
import com.kijlee.android.demo.ui.main.FgTabLayout
import com.kijlee.android.demo.ui.tablayout.FgTab1List
import com.kijlee.android.demo.ui.tablayout.Vp1Adapter

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.fragmentsave
 * @ClassName:      FragmentSaveB
 * @Author:     kij
 * @Description:  用于fragment跳转后返回上个界面查看界面数据恢复问题
 * @Date:    2023/1/12 13:39
 * @Version:    1.0
 */
class FragmentSaveB : Fragment() {
    var _binding: FragmentSaveBBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSaveBBinding.inflate(layoutInflater)
        super.onCreateView(inflater, container, savedInstanceState)
        val root: View = binding.root
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}