package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapAlert
import com.beardedhen.androidbootstrap.BootstrapAlert.VisibilityChangeListener
import com.kijlee.android.demo.databinding.ExampleBootstrapAlertBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapAlertExample
 * @Author:     kij
 * @Description:  FgBootstrapAlertExample
 * @Date:    2022/1/11 8:03 下午
 * @Version:    1.0
 */
class FgBootstrapAlertExample: Fragment() {
    var _layoutBind: ExampleBootstrapAlertBinding? = null
    var item = ""
    val TAG = "BootstrapAlertExample"

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

        _layoutBind = ExampleBootstrapAlertBinding.inflate(layoutInflater)

        val root: View = binding.root



        binding.dynamicAlert.setVisibilityChangeListener(object : VisibilityChangeListener {
            override fun onAlertDismissStarted(alert: BootstrapAlert) {
                Log.d(TAG, "Started dismissing alert!"
                )
            }

            override fun onAlertDismissCompletion(alert: BootstrapAlert) {
                Log.d(TAG, "Finished dismissing alert!"
                )
            }

            override fun onAlertAppearStarted(alert: BootstrapAlert) {
                Log.d(TAG, "Started appearing alert!"
                )
            }

            override fun onAlertAppearCompletion(alert: BootstrapAlert) {
                Log.d(TAG, "Finished appearing alert!"
                )
            }
        })
        binding.interactiveButton.setOnClickListener{

            if (View.GONE == binding.dynamicAlert.getVisibility()) {
                binding.dynamicAlert.show(true)
            } else {
                binding.dynamicAlert.dismiss(true)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}