package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapButtonBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapButtonExample
 * @Author:     kij
 * @Description:  FgBootstrapButtonExample
 * @Date:    2022/1/11 7:57 下午
 * @Version:    1.0
 */
class FgBootstrapButtonExample : Fragment() {
    var _layoutBind: ExampleBootstrapButtonBinding? = null
    var item = ""
    private var size = DefaultBootstrapSize.LG
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

        _layoutBind = ExampleBootstrapButtonBinding.inflate(layoutInflater)

        val root: View = binding.root
        setupCustomStyle()
        binding.setOnClickListener {
            when (it.id) {
                R.id.bbutton_example_corners -> {
                    binding.bbuttonExampleCorners.setRounded(!binding.bbuttonExampleCorners.isRounded())
                }
                R.id.bbutton_example_outline -> {
                    binding.bbuttonExampleOutline.setShowOutline(!binding.bbuttonExampleOutline.isShowOutline())
                }
                R.id.bbutton_example_size -> {
                    when (size) {
                        DefaultBootstrapSize.XS -> size = DefaultBootstrapSize.SM
                        DefaultBootstrapSize.SM -> size = DefaultBootstrapSize.MD
                        DefaultBootstrapSize.MD -> size = DefaultBootstrapSize.LG
                        DefaultBootstrapSize.LG -> size = DefaultBootstrapSize.XL
                        DefaultBootstrapSize.XL -> size = DefaultBootstrapSize.XS
                    }
                    binding.bbuttonExampleSize.setBootstrapSize(size)

                }
                R.id.bbutton_example_theme -> {

                    when (binding.bbuttonExampleTheme.getBootstrapBrand()) {
                        DefaultBootstrapBrand.PRIMARY -> binding.bbuttonExampleTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.SUCCESS
                        )
                        DefaultBootstrapBrand.SUCCESS -> binding.bbuttonExampleTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.WARNING
                        )
                        DefaultBootstrapBrand.WARNING -> binding.bbuttonExampleTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.DANGER
                        )
                        DefaultBootstrapBrand.DANGER -> binding.bbuttonExampleTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.INFO
                        )
                        DefaultBootstrapBrand.INFO -> binding.bbuttonExampleTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.SECONDARY
                        )
                        DefaultBootstrapBrand.SECONDARY -> binding.bbuttonExampleTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.REGULAR
                        )
                        DefaultBootstrapBrand.REGULAR -> binding.bbuttonExampleTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.PRIMARY
                        )
                    }
                }
            }
        }


        return root
    }

    fun setupCustomStyle() {

        // create a custom bootstrap size
        binding.exampleBbuttonCustomStyle.setBootstrapSize(3.0f)
        // create a Bootstrap Theme with holo colors
        binding.exampleBbuttonCustomStyle.setBootstrapBrand(CustomBootstrapStyle(requireContext()))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}