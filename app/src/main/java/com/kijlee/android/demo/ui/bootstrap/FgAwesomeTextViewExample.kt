package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.beardedhen.androidbootstrap.AwesomeTextView
import com.beardedhen.androidbootstrap.BootstrapText
import com.beardedhen.androidbootstrap.font.FontAwesome
import com.beardedhen.androidbootstrap.font.MaterialIcons
import com.beardedhen.androidbootstrap.font.Typicon
import com.kijlee.android.demo.R
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

    private var android = true
    private var wikipedia = true
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
        setupFontAwesomeText()

        binding.setOnClickListener {
            when (it.id) {

                R.id.example_fa_text_change -> {
                    android = !android
                    binding.exampleFaTextChange.setFontAwesomeIcon(if (android) FontAwesome.FA_ANDROID else FontAwesome.FA_APPLE)
                }
                R.id.example_fa_text_multi_change -> {
                    wikipedia = !wikipedia
                    val text =
                        if (wikipedia) "{fa_image} is in the {fa_cloud}" else "{fa_bank} are on {fa_globe}"
                    binding.exampleFaTextMultiChange.setMarkdownText(text)
                }

            }
        }
        binding.changeText.setOnClickListener {
            binding.testText.text = "西瓜瓜"
        }
        binding.testText.setOnClickListener {
            it.findNavController().navigate(R.id.to_test_demo)
        }
        return root
    }

    private fun setupFontAwesomeText() {
        binding.exampleFaTextFlash.startFlashing(true, AwesomeTextView.AnimationSpeed.FAST)
        binding.exampleFaTextRotate.startRotate(true, AwesomeTextView.AnimationSpeed.SLOW)

        val text = BootstrapText.Builder(requireContext())
            .addText("I ")
            .addFontAwesomeIcon(FontAwesome.FA_HEART)
            .addText(" going on ")
            .addFontAwesomeIcon(FontAwesome.FA_TWITTER)
            .build()

        binding.exampleFaTextBuilder.setBootstrapText(text)

        binding.exampleMixAndMatch.setBootstrapText(
            BootstrapText.Builder(requireContext())
                .addFontAwesomeIcon(FontAwesome.FA_ANCHOR)
                .addTypicon(Typicon.TY_CODE)
                .addMaterialIcon(MaterialIcons.MD_PHOTO)
                .build()
        )


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }

}