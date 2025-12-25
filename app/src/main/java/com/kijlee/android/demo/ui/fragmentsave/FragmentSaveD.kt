package com.kijlee.android.demo.ui.fragmentsave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FragmentSaveBBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.fragmentsave
 * @ClassName:      FragmentSaveD
 * @Author:     kij
 * @Description:  a--b-c-d
 * @Date:    2025/11/26 17:58
 * @Version:    1.0
 */
class FragmentSaveD  : Fragment() {
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
        binding.backBtn.text = "FragmentD返回C"
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toNextBtn.setOnClickListener {
            findNavController().navigate(R.id.to_fragment_save_a)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}