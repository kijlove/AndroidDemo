package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapEditTextViewBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapEditTextExample
 * @Author:     kij
 * @Description:  FgBootstrapEditTextExample
 * @Date:    2022/1/11 8:02 下午
 * @Version:    1.0
 */
class FgBootstrapEditTextExample : Fragment() {
    var _layoutBind: ExampleBootstrapEditTextViewBinding? = null
    var item = ""

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

        _layoutBind = ExampleBootstrapEditTextViewBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.setOnClickListener {
            when (it.id) {
                R.id.bedit_text_change_enabled_btn -> {
                    binding.beditTextChangeEnabled.setEnabled(!binding.beditTextChangeEnabled.isEnabled())

                }
                R.id.bedit_text_change_round_btn -> {
                    binding.beditTextChangeRound.setRounded(!binding.beditTextChangeRound.isRounded())

                }
                R.id.bedit_text_change_theme_btn -> {

                    when (binding.beditTextChangeTheme.getBootstrapBrand()) {
                        DefaultBootstrapBrand.PRIMARY -> binding.beditTextChangeTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.SUCCESS
                        )
                        DefaultBootstrapBrand.SUCCESS -> binding.beditTextChangeTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.INFO
                        )
                        DefaultBootstrapBrand.INFO -> binding.beditTextChangeTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.WARNING
                        )
                        DefaultBootstrapBrand.WARNING -> binding.beditTextChangeTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.DANGER
                        )
                        DefaultBootstrapBrand.DANGER -> binding.beditTextChangeTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.SECONDARY
                        )
                        DefaultBootstrapBrand.SECONDARY -> binding.beditTextChangeTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.REGULAR
                        )
                        DefaultBootstrapBrand.REGULAR -> binding.beditTextChangeTheme.setBootstrapBrand(
                            DefaultBootstrapBrand.PRIMARY
                        )
                    }
                }
                R.id.bedit_text_change_size_btn -> {

                    when (size) {
                        DefaultBootstrapSize.XS -> size = DefaultBootstrapSize.SM
                        DefaultBootstrapSize.SM -> size = DefaultBootstrapSize.MD
                        DefaultBootstrapSize.MD -> size = DefaultBootstrapSize.LG
                        DefaultBootstrapSize.LG -> size = DefaultBootstrapSize.XL
                        DefaultBootstrapSize.XL -> size = DefaultBootstrapSize.XS
                    }
                    binding.beditTextChangeSize.setBootstrapSize(size)
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