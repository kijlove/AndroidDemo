package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapBadge
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapBadgeBinding
import java.util.*

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapBadgeExample
 * @Author:     kij
 * @Description:  FgBootstrapBadgeExample
 * @Date:    2022/1/11 8:03 下午
 * @Version:    1.0
 */
class FgBootstrapBadgeExample: Fragment() {
    var _layoutBind: ExampleBootstrapBadgeBinding? = null
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

        _layoutBind = ExampleBootstrapBadgeBinding.inflate(layoutInflater)

        val root: View = binding.root


        val badgeThird = BootstrapBadge(requireContext())
        badgeThird.badgeText = "Hi!"
        binding.javaBadgeButton.setBadge(badgeThird)

        binding.setOnClickListener{
            when (it.id){
                R.id.lonely_badge->{
                    binding.lonelyBadge.setBadgeText(Random().nextInt().toString())
                }
                R.id.xml_badge_button->{
                    binding.xmlBadgeButton.setBadgeText(Random().nextInt().toString())
                }
                R.id.java_badge_button->{
                    binding.javaBadgeButton.setBadgeText(Random().nextInt().toString())
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