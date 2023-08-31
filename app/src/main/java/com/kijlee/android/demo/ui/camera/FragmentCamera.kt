package com.kijlee.android.demo.ui.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FragmentCameraBinding
import com.kijlee.android.demo.ui.main.FgMain

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FragmentCamera
 * @Author:     kij
 * @Description:  相机功能
 * @Date:    2023/8/31 11:05
 * @Version:    1.0
 */
class FragmentCamera : Fragment() {
    var _layoutBind: FragmentCameraBinding? = null
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

                Toast.makeText(requireContext(), "$item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FragmentCameraBinding.inflate(layoutInflater)
        val root: View = binding.root


        return root
    }

    //fun that print a random number



    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}