package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapCircleThumbnailBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapCircleThumbnailExample
 * @Author:     kij
 * @Description:  FgBootstrapCircleThumbnailExample
 * @Date:    2022/1/11 8:02 下午
 * @Version:    1.0
 */
class FgBootstrapCircleThumbnailExample : Fragment() {
    var _layoutBind: ExampleBootstrapCircleThumbnailBinding? = null
    var item = ""

    private val BASELINE_SIZE = 300f

    private var resId = R.drawable.ladybird
    private var size = DefaultBootstrapSize.MD
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

        _layoutBind = ExampleBootstrapCircleThumbnailBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.setOnClickListener {
            when (it.id) {
                R.id.bcircle_theme_change_example -> {

                    when (binding.bcircleThemeChangeExample.getBootstrapBrand()) {
                        DefaultBootstrapBrand.PRIMARY -> binding.bcircleThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.SUCCESS
                        )
                        DefaultBootstrapBrand.SUCCESS -> binding.bcircleThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.INFO
                        )
                        DefaultBootstrapBrand.INFO -> binding.bcircleThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.WARNING
                        )
                        DefaultBootstrapBrand.WARNING -> binding.bcircleThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.DANGER
                        )
                        DefaultBootstrapBrand.DANGER -> binding.bcircleThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.SECONDARY
                        )
                        DefaultBootstrapBrand.SECONDARY -> binding.bcircleThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.REGULAR
                        )
                        DefaultBootstrapBrand.REGULAR -> binding.bcircleThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.PRIMARY
                        )
                    }
                }
                R.id.bcircle_image_change_example -> {
                    when (resId) {
                        R.drawable.ladybird -> resId = R.drawable.caterpillar
                        R.drawable.caterpillar -> resId = 0
                        0 -> resId = R.drawable.ladybird
                    }
                    binding.bcircleImageChangeExample.setImageResource(resId)
                }
                R.id.bcircle_border_change_example -> {
                    binding.bcircleBorderChangeExample.setBorderDisplayed(!binding.bcircleBorderChangeExample.isBorderDisplayed())
                }
                R.id.bcircle_size_change_example -> {

                    when (size) {
                        DefaultBootstrapSize.XS -> size = DefaultBootstrapSize.SM
                        DefaultBootstrapSize.SM -> size = DefaultBootstrapSize.MD
                        DefaultBootstrapSize.MD -> size = DefaultBootstrapSize.LG
                        DefaultBootstrapSize.LG -> size = DefaultBootstrapSize.XL
                        DefaultBootstrapSize.XL -> size = DefaultBootstrapSize.XS
                    }
                    binding.bcircleSizeChangeExample.setBootstrapSize(size)
                    binding.bcircleSizeChangeExample.setLayoutParams(getLayoutParams(size.scaleFactor()))
                }
            }

        }

        return root
    }

    private fun getLayoutParams(factor: Float): LinearLayout.LayoutParams? {
        val size: Float = BASELINE_SIZE * factor
        return LinearLayout.LayoutParams(size.toInt(), size.toInt())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}