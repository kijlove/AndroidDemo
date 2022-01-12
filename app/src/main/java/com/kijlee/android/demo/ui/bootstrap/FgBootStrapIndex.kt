package com.kijlee.android.demo.ui.bootstrap

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
import com.kijlee.android.demo.databinding.FgBootStrapIndexBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgBootStrap
 * @Author:     kij
 * @Description:  bootstrap例子
 * @Date:    2022/1/7 9:24 下午
 * @Version:    1.0
 */
class FgBootStrapIndex : Fragment() {


    var _layoutBind: FgBootStrapIndexBinding? = null
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

        _layoutBind = FgBootStrapIndexBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.demoName = item
        //        "{fa-github} Fork me on Github {fa-heart}"
        binding.setOnClickListener {
            val demoName = (it as BootstrapButton).text.toString()

            var bundle = Bundle()
            bundle.putString(ARG_ITEM_ID, demoName)

            when (it.id) {
                R.id.github_btn -> {
                    val intent = Intent(Intent.ACTION_VIEW)
                    startActivity(intent)
                    intent.data = Uri.parse("https://github.com/Bearded-Hen/Android-Bootstrap")
                    startActivity(intent)
                }
                R.id.example_bootstrap_button -> {
                    it.findNavController().navigate(R.id.example_bootstrap_button, bundle)
                }
                R.id.example_bootstrap_btn_group -> {
                    it.findNavController().navigate(R.id.example_bootstrap_button_group, bundle)
                }
                R.id.example_fontawesometext -> {
                    it.findNavController().navigate(R.id.example_awesome_text_view, bundle)
                }
                R.id.example_bootstrap_label -> {
                    it.findNavController().navigate(R.id.example_bootstrap_label, bundle)
                }
                R.id.example_bootstrap_progress -> {
                    it.findNavController().navigate(R.id.example_bootstrap_progress_bar, bundle)
                }
                R.id.example_bootstrap_progress_group -> {
                    it.findNavController().navigate(R.id.example_bootstrap_progress_bar_group, bundle)
                }
                R.id.example_bootstrap_cricle_thumbnail -> {
                    it.findNavController().navigate(R.id.example_bootstrap_circle_thumbnail, bundle)
                }
                R.id.example_bootstrap_thumbnail -> {
                    it.findNavController().navigate(R.id.example_bootstrap_thumbnail, bundle)
                }
                R.id.example_bootstrap_edit_text -> {
                    it.findNavController().navigate(R.id.example_bootstrap_edit_text_view, bundle)
                }
                R.id.example_bootstrap_well -> {
                    it.findNavController().navigate(R.id.example_bootstrap_well, bundle)
                }
                R.id.example_bootstrap_dropdown -> {
                    it.findNavController().navigate(R.id.example_bootstrap_dropdown, bundle)
                }
                R.id.example_bootstrap_alert -> {
                    it.findNavController().navigate(R.id.example_bootstrap_alert, bundle)
                }
                R.id.example_bootstrap_badge -> {
                    it.findNavController().navigate(R.id.example_bootstrap_badge, bundle)
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