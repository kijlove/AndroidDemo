package com.kijlee.android.demo.ui.netdemo

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.App
import com.kijlee.android.demo.databinding.FragmentNetDemoBinding
import com.kijlee.android.demo.ui.main.FgMain
import com.orhanobut.logger.Logger
import org.apache.http.params.HttpParams


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.netdemo
 * @ClassName:      FragmentApacheHttpDemo
 * @Author:     kij
 * @Description:  apache网络交互示例
 * @Date:    2023/1/16 21:09
 * @Version:    1.0
 */
class FragmentApacheHttpDemo : Fragment() {
    var _binding: FragmentNetDemoBinding? = null
    private val binding get() = _binding!!
    var item = ""

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
        _binding = FragmentNetDemoBinding.inflate(layoutInflater)
        super.onCreateView(inflater, container, savedInstanceState)
        val root: View = binding.root
        onClick()
        initView()

        return root
    }

    fun initView() {
    }

    fun onClick() {
        binding.apacheGet.setOnClickListener{
            Logger.e("发送get请求")
        }
        binding.apachePost.setOnClickListener{
            Logger.e("发送post请求")
        }
    }


    fun apacheGetNet(){

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}