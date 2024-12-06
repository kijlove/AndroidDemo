package com.kijlee.android.demo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.base
 * @ClassName:      BaseFragment
 * @Author:     kij
 * @Description:
 * @Date:    2024/11/2 15:02
 * @Version:    1.0
 */
open class BaseFragment<VB: ViewBinding>:Fragment() {
    var _binding :VB? = null
    val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}