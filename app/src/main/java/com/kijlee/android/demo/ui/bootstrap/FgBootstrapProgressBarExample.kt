package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapProgressBarBinding
import java.util.*

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapProgressBarExample
 * @Author:     kij
 * @Description:  FgBootstrapProgressBarExample
 * @Date:    2022/1/11 7:58 下午
 * @Version:    1.0
 */
class FgBootstrapProgressBarExample : Fragment() {
    var _layoutBind: ExampleBootstrapProgressBarBinding? = null
    var item = ""
    private var changeState = ChangeState.FIRST
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

        _layoutBind = ExampleBootstrapProgressBarBinding.inflate(layoutInflater)

        val root: View = binding.root

        binding.setOnClickListener {
            when (it.id) {

                R.id.example_progress_default_btn -> {
                    binding.exampleProgressDefault.setProgress(randomProgress(binding.exampleProgressDefault.getProgress(), 100))
                }
                R.id.example_progress_animated_btn -> {
                    binding.exampleProgressAnimated.setProgress(randomProgress(binding.exampleProgressAnimated.getProgress(), 100))
                }
                R.id.example_progress_striped_btn -> {
                    binding.exampleProgressStriped.setProgress(randomProgress(binding.exampleProgressStriped.getProgress(), 200))
                }
                R.id.example_progress_striped_animated_btn -> {
                    binding.exampleProgressStripedAnimated.setProgress(randomProgress(binding.exampleProgressStripedAnimated.getProgress(), 100))
                }
                R.id.example_progress_change_rounded_btn -> {
                    binding.exampleProgressChange.setRounded(!  binding.exampleProgressChange.isRounded())
                }
                R.id.example_progress_change_type_btn -> {

                    changeState = changeState.next()
                    binding.exampleProgressChange.setStriped(changeState.striped)
                    binding.exampleProgressChange.setAnimated(changeState.animated)
                }
                R.id.example_size_change_btn -> {
                    when (size) {
                        DefaultBootstrapSize.XS -> size = DefaultBootstrapSize.SM
                        DefaultBootstrapSize.SM -> size = DefaultBootstrapSize.MD
                        DefaultBootstrapSize.MD -> size = DefaultBootstrapSize.LG
                        DefaultBootstrapSize.LG -> size = DefaultBootstrapSize.XL
                        DefaultBootstrapSize.XL -> size = DefaultBootstrapSize.XS
                    }
                    binding.exampleSizeChange.setBootstrapSize(size)
                }
                R.id.example_progress_change_color_btn -> {

                    when (binding.exampleProgressChange.getBootstrapBrand()) {
                        DefaultBootstrapBrand.PRIMARY -> binding.exampleProgressChange.setBootstrapBrand(
                            DefaultBootstrapBrand.SUCCESS
                        )
                        DefaultBootstrapBrand.SUCCESS -> binding.exampleProgressChange.setBootstrapBrand(
                            DefaultBootstrapBrand.INFO
                        )
                        DefaultBootstrapBrand.INFO -> binding.exampleProgressChange.setBootstrapBrand(
                            DefaultBootstrapBrand.WARNING
                        )
                        DefaultBootstrapBrand.WARNING -> binding.exampleProgressChange.setBootstrapBrand(
                            DefaultBootstrapBrand.DANGER
                        )
                        DefaultBootstrapBrand.DANGER -> binding.exampleProgressChange.setBootstrapBrand(
                            DefaultBootstrapBrand.SECONDARY
                        )
                        DefaultBootstrapBrand.SECONDARY -> {
                            binding.exampleProgressChange.setBootstrapBrand(DefaultBootstrapBrand.REGULAR)
                            binding.exampleProgressChange.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY)
                        }
                        DefaultBootstrapBrand.REGULAR -> binding.exampleProgressChange.setBootstrapBrand(
                            DefaultBootstrapBrand.PRIMARY
                        )
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
    private var random: Random? = null

    private fun randomProgress(currentProgress: Int, maxProgress: Int): Int {
        if (random == null) {
            random = Random()
        }
        var prog: Int = currentProgress + random!!.nextInt(20)
        if (prog > maxProgress) {
            prog -= maxProgress
        }
        return prog
    }


    internal enum class ChangeState( val animated: Boolean, val striped: Boolean) {
        FIRST(false, false),
        SECOND(false, true),
        THIRD(true, false),
        FOURTH(true, true);

        operator fun next(): ChangeState {
            return when (this) {
                FIRST -> SECOND
                SECOND -> THIRD
                THIRD -> FOURTH
                FOURTH -> FIRST
            }
        }
    }

}