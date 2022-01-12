package com.kijlee.android.demo.ui.bootstrap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize
import com.beardedhen.androidbootstrap.utils.DrawableUtils
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapThumbnailBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapThumbnailExample
 * @Author:     kij
 * @Description:  FgBootstrapThumbnailExample
 * @Date:    2022/1/11 8:03 下午
 * @Version:    1.0
 */
class FgBootstrapThumbnailExample : Fragment() {
    var _layoutBind: ExampleBootstrapThumbnailBinding? = null
    var item = ""

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

        _layoutBind = ExampleBootstrapThumbnailBinding.inflate(layoutInflater)

        val root: View = binding.root


        val bm = BitmapFactory.decodeResource(resources, R.drawable.small_daffodils)
        binding.bthumbSetImageBitmapExample.setImageBitmap(bm)

        binding.bthumbSetImageDrawableExample.setImageDrawable(
            DrawableUtils.resolveDrawable(
                R.drawable.ladybird,
                requireContext()))
        binding.bthumbSetImageResourceExample.setImageResource(R.drawable.caterpillar)
        binding.bthumbSizeChangeExample.setLayoutParams(getLayoutParams(size.scaleFactor()))

        binding.setOnClickListener {
            when (it.id) {
                R.id.bthumb_theme_change_example -> {

                    when (binding.bthumbThemeChangeExample.getBootstrapBrand()) {
                        DefaultBootstrapBrand.PRIMARY -> binding.bthumbThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.SUCCESS
                        )
                        DefaultBootstrapBrand.SUCCESS -> binding.bthumbThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.INFO
                        )
                        DefaultBootstrapBrand.INFO -> binding.bthumbThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.WARNING
                        )
                        DefaultBootstrapBrand.WARNING -> binding.bthumbThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.DANGER
                        )
                        DefaultBootstrapBrand.DANGER -> binding.bthumbThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.SECONDARY
                        )
                        DefaultBootstrapBrand.SECONDARY -> binding.bthumbThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.REGULAR
                        )
                        DefaultBootstrapBrand.REGULAR -> binding.bthumbThemeChangeExample.setBootstrapBrand(
                            DefaultBootstrapBrand.PRIMARY
                        )
                    }
                }
                R.id.bthumb_image_change_example -> {

                    when (resId) {
                        R.drawable.ladybird -> resId = R.drawable.caterpillar
                        R.drawable.caterpillar -> resId = 0
                        0 -> resId = R.drawable.ladybird
                    }
                    binding.bthumbImageChangeExample.setImageResource(resId)
                }
                R.id.bthumb_rounded_change_example -> {
                    binding.bthumbRoundedChangeExample.setRounded(!binding.bthumbRoundedChangeExample.isRounded())

                }
                R.id.bthumb_border_change_example -> {
                    binding.bthumbBorderChangeExample.setBorderDisplayed(!binding.bthumbBorderChangeExample.isBorderDisplayed())
                }
                R.id.bthumb_size_change_example -> {

                    when (size) {
                        DefaultBootstrapSize.XS -> size = DefaultBootstrapSize.SM
                        DefaultBootstrapSize.SM -> size = DefaultBootstrapSize.MD
                        DefaultBootstrapSize.MD -> size = DefaultBootstrapSize.LG
                        DefaultBootstrapSize.LG -> size = DefaultBootstrapSize.XL
                        DefaultBootstrapSize.XL -> size = DefaultBootstrapSize.XS
                    }
                    binding.bthumbSizeChangeExample.setBootstrapSize(size)
                    binding.bthumbSizeChangeExample.setLayoutParams(getLayoutParams(size.scaleFactor()))
                }
            }
        }

        return root
    }

    private fun getLayoutParams(factor: Float): LinearLayout.LayoutParams? {
        val baselineSize = 300f
        val size = baselineSize * factor
        return LinearLayout.LayoutParams(size.toInt(), size.toInt())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}