package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapProgressBar
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapProgressBarGroupBinding
import java.util.*

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapProgressBarGroupExample
 * @Author:     kij
 * @Description:  FgBootstrapProgressBarGroupExample
 * @Date:    2022/1/11 7:58 下午
 * @Version:    1.0
 */
class FgBootstrapProgressBarGroupExample : Fragment() {
    var _layoutBind: ExampleBootstrapProgressBarGroupBinding? = null
    var item = ""
    var rounded = false

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

        _layoutBind = ExampleBootstrapProgressBarGroupBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.setOnClickListener {
            when (root.id) {
                R.id.example_progress_bar_group_add -> {

                    val rand = Random()
                    val bar = BootstrapProgressBar(requireContext())
                    bar.progress = 10
                    var brand = 5
                    while (brand == 5) {
                        brand = rand.nextInt(7)
                    }
                    bar.bootstrapBrand = DefaultBootstrapBrand.fromAttributeValue(brand)

                    if (binding.exampleProgressBarGroupAddGroup.getCumulativeProgress() + 10 <= 100) {
                        binding.exampleProgressBarGroupAddGroup.addView(bar)
                    } else {
                        binding.exampleProgressBarGroupAddGroup.removeViews(2, binding.exampleProgressBarGroupAddGroup.getChildCount() - 3)
                    }
                }
                R.id.example_progress_bar_group_round -> {
                    rounded = !rounded
                    binding.exampleProgressBarGroupRoundGroup.setRounded(rounded)
                }
                R.id.example_progress_bar_group_progress -> {

                    val rand = Random()
                    val progress = rand.nextInt(30) + 10
                    when (rand.nextInt(2)) {
                        0 -> binding.exampleProgressBarGroupProgress1.setProgress(progress)
                        1 -> binding.exampleProgressBarGroupProgress2.setProgress(progress)
                    }
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