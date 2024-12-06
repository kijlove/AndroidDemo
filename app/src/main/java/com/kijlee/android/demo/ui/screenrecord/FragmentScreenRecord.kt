package com.kijlee.android.demo.ui.screenrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FragmentMyViewBinding
import com.kijlee.android.demo.databinding.FragmentScreenRecordBinding
import com.kijlee.android.demo.ui.main.FgMain

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.screenrecord
 * @ClassName:      FragmentScreenRecord
 * @Author:     kij
 * @Description:  录制屏幕功能
 * @Date:    2024/3/14 09:51
 * @Version:    1.0
 */
class FragmentScreenRecord  : Fragment() {
    var _layoutBind: FragmentScreenRecordBinding? = null
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

        _layoutBind = FragmentScreenRecordBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.startScreen.text = item
        return root
    }

    //fun that print a random number



    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}