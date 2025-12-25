package com.kijlee.android.demo.ui.fragmentsave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FragmentSaveABinding
import com.kijlee.android.demo.ui.main.FgMain
import kotlin.random.Random

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.fragmentsave
 * @ClassName:      FragmentSaveA
 * @Author:     kij
 * @Description:  用于测试fragment中数据保存问题
 * @Date:    2023/1/12 13:38
 * @Version:    1.0
 */
class FragmentSaveA : Fragment() {
    var _binding: FragmentSaveABinding? = null
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

        _binding = FragmentSaveABinding.inflate(layoutInflater)
        super.onCreateView(inflater, container, savedInstanceState)
        val root: View = binding.root
        onClick()
        initView()

        return root
    }

    fun initView() {
    }

    fun onClick() {
        binding.changeTextBtn.setOnClickListener {
            binding.testText1.text = "随机数生成===" + Random.nextInt(1, 100).toString()
        }
        binding.toNextFragment.setOnClickListener {
            findNavController().navigate(R.id.to_fragment_save_b)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}