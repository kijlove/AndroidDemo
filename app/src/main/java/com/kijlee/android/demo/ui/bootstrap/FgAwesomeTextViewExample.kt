package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.ExampleAwesomeTextViewBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgAwesomeTextViewExample
 * @Author:     kij
 * @Description:  FgAwesomeTextViewExample
 * @Date:    2022/1/11 7:58 下午
 * @Version:    1.0
 */
class FgAwesomeTextViewExample : Fragment() {
    var _layoutBind: ExampleAwesomeTextViewBinding? = null
    var item = ""

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _layoutBind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgBootStrapIndex.ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgBootStrapIndex.ARG_ITEM_ID).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _layoutBind = ExampleAwesomeTextViewBinding.inflate(layoutInflater)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }

}