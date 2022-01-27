package com.kijlee.android.demo.ui.sqllite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FgBootStrapBinding
import com.kijlee.android.demo.databinding.FgGreenDaoBinding
import com.kijlee.android.demo.ui.main.FgMain
import com.kijlee.android.demo.ui.main.FgSqlLite

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.sqllite
 * @ClassName:      FgGreenDao
 * @Author:     kij
 * @Description:  GreenDao数据库用法
 * @Date:    2022/1/26 2:46 下午
 * @Version:    1.0
 */
class FgGreenDao : Fragment() {


    var _layoutBind: FgGreenDaoBinding? = null
    var item = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgSqlLite.SqlLite_Name)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgSqlLite.SqlLite_Name).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgGreenDaoBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.sqlName = item

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }



}