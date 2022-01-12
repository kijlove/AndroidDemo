package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapHeading
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapLabelBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapLabelExample
 * @Author:     kij
 * @Description:  FgBootstrapLabelExample
 * @Date:    2022/1/11 7:58 下午
 * @Version:    1.0
 */
class FgBootstrapLabelExample : Fragment() {
    var _layoutBind: ExampleBootstrapLabelBinding? = null
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

        _layoutBind = ExampleBootstrapLabelBinding.inflate(layoutInflater)

        val root: View = binding.root

        binding.setOnClickListener {
            when (it.id) {

                R.id.example_blabel_change_color -> {

                    when (binding.exampleBlabelChangeColor.getBootstrapBrand()) {
                        DefaultBootstrapBrand.PRIMARY -> binding.exampleBlabelChangeColor.setBootstrapBrand(
                            DefaultBootstrapBrand.SUCCESS
                        )
                        DefaultBootstrapBrand.SUCCESS -> binding.exampleBlabelChangeColor.setBootstrapBrand(
                            DefaultBootstrapBrand.INFO
                        )
                        DefaultBootstrapBrand.INFO -> binding.exampleBlabelChangeColor.setBootstrapBrand(
                            DefaultBootstrapBrand.WARNING
                        )
                        DefaultBootstrapBrand.WARNING -> binding.exampleBlabelChangeColor.setBootstrapBrand(
                            DefaultBootstrapBrand.DANGER
                        )
                        DefaultBootstrapBrand.DANGER -> binding.exampleBlabelChangeColor.setBootstrapBrand(
                            DefaultBootstrapBrand.SECONDARY
                        )
                        DefaultBootstrapBrand.SECONDARY -> binding.exampleBlabelChangeColor.setBootstrapBrand(
                            DefaultBootstrapBrand.REGULAR
                        )
                        DefaultBootstrapBrand.REGULAR -> binding.exampleBlabelChangeColor.setBootstrapBrand(
                            DefaultBootstrapBrand.PRIMARY
                        )
                        else -> binding.exampleBlabelChangeColor.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY)
                    }
                }
                R.id.example_blabel_change_heading -> {

                    when (binding.exampleBlabelChangeHeading.getBootstrapHeading()) {
                        DefaultBootstrapHeading.H1 -> binding.exampleBlabelChangeHeading.setBootstrapHeading(
                            DefaultBootstrapHeading.H2
                        )
                        DefaultBootstrapHeading.H2 -> binding.exampleBlabelChangeHeading.setBootstrapHeading(
                            DefaultBootstrapHeading.H3
                        )
                        DefaultBootstrapHeading.H3 -> binding.exampleBlabelChangeHeading.setBootstrapHeading(
                            DefaultBootstrapHeading.H4
                        )
                        DefaultBootstrapHeading.H4 -> binding.exampleBlabelChangeHeading.setBootstrapHeading(
                            DefaultBootstrapHeading.H5
                        )
                        DefaultBootstrapHeading.H5 -> binding.exampleBlabelChangeHeading.setBootstrapHeading(
                            DefaultBootstrapHeading.H6
                        )
                        DefaultBootstrapHeading.H6 -> binding.exampleBlabelChangeHeading.setBootstrapHeading(
                            DefaultBootstrapHeading.H1
                        )
                        else -> binding.exampleBlabelChangeHeading.setBootstrapHeading(DefaultBootstrapHeading.H1)
                    }

                }
                R.id.example_blabel_change_rounded -> {
                    binding.exampleBlabelChangeRounded.setRounded(!binding.exampleBlabelChangeRounded.isRounded())

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