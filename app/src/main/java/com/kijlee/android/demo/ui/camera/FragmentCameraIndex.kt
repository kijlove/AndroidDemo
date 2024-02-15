package com.kijlee.android.demo.ui.camera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.beardedhen.androidbootstrap.BootstrapButton
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FragmentCameraIndexBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.camera
 * @ClassName:      FragmentCameraIndex
 * @Author:     kij
 * @Description:  相机功能界面选择
 * @Date:    2023/10/5 09:59
 * @Version:    1.0
 */
class FragmentCameraIndex : Fragment() {


    var _layoutBind: FragmentCameraIndexBinding? = null
    var item = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(ARG_ITEM_ID).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FragmentCameraIndexBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.demoName = item
        //        "{fa-github} Fork me on Github {fa-heart}"
        binding.setOnClickListener {
            val demoName = (it as BootstrapButton).text.toString()

            val bundle = Bundle()
            bundle.putString(ARG_ITEM_ID, demoName)

            when (it.id) {
                R.id.to_zxing -> {
                    val intent = Intent(requireContext(), CaptureActivity::class.java)
                    startActivity(intent)

//                    it.findNavController().navigate(R.id.to_fragment_zxing_demo)

                }
            }
        }


        return root
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}