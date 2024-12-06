package com.kijlee.android.demo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FragmentMyViewBinding
import com.kijlee.android.demo.ui.main.FgMain

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.views
 * @ClassName:      FragmentMyView
 * @Author:     kij
 * @Description:  自定义View
 * @Date:    2024/2/22 22:22
 * @Version:    1.0
 */
class FragmentMyView : Fragment() {
    var _layoutBind: FragmentMyViewBinding? = null
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

        _layoutBind = FragmentMyViewBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.text.text = item
        return root
    }

    //fun that print a random number



    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}