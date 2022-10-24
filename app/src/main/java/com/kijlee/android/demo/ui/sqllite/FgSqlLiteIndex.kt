package com.kijlee.android.demo.ui.sqllite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.beardedhen.androidbootstrap.BootstrapButton
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgSqlLiteIndexBinding
import com.kijlee.android.demo.ui.main.FgMain
import com.kijlee.android.demo.ui.main.FgSqlLite

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.sqllite
 * @ClassName:      Fg_SqlLiteIndex
 * @Author:     kij
 * @Description:  数据库整理主页
 * @Date:    2022/1/26 1:31 下午
 * @Version:    1.0
 */
class FgSqlLiteIndex : Fragment() {


    var _layoutBind: FgSqlLiteIndexBinding? = null
    var item = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
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

        _layoutBind = FgSqlLiteIndexBinding.inflate(layoutInflater)

        val root: View = binding.root

        binding.setOnClickListener {

            val demoName = (it as BootstrapButton).text.toString()

            var bundle = Bundle()
            bundle.putString(FgSqlLite.SqlLite_Name, demoName)
            when(it.id){
                R.id.to_green_dao->{//GreenDao数据库使用
                    it.findNavController().navigate(R.id.to_green_dao, bundle)
                }
                R.id.to_object_box->{//GreenDao数据库使用
                    it.findNavController().navigate(R.id.to_object_box, bundle)
                }
            }
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }


}