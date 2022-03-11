package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FgBootStrapBinding
import com.kijlee.android.demo.databinding.FgSqlLiteBinding
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgGreenDao
 * @Author:     kij
 * @Description:  greendao数据库
 * @Date:    2022/1/26 1:14 下午
 * @Version:    1.0
 */
class FgSqlLite: Fragment() {


    var _layoutBind: FgSqlLiteBinding? = null
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
            item = FgSqlLiteArgs.fromBundle(requireArguments()).demoName
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgSqlLiteBinding.inflate(layoutInflater)

        val root: View = binding.root
Logger.e("$item")

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }


    companion object {

        val SqlLite_Name = "sql_lite_name"
    }
}