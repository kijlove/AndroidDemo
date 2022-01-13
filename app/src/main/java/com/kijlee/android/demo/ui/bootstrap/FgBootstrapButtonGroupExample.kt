package com.kijlee.android.demo.ui.bootstrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.BootstrapButton
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ExampleBootstrapButtonGroupBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.bootstrap
 * @ClassName:      FgBootstrapButtonGroupExample
 * @Author:     kij
 * @Description:  FgBootstrapButtonGroupExample
 * @Date:    2022/1/11 8:02 下午
 * @Version:    1.0
 */
class FgBootstrapButtonGroupExample : Fragment() {
    var _layoutBind: ExampleBootstrapButtonGroupBinding? = null
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

        _layoutBind = ExampleBootstrapButtonGroupBinding.inflate(layoutInflater)

        val root: View = binding.root

        binding.bbuttonGroupChecked1.setOnCheckedChangedListener { _, isChecked ->
            if (isChecked) {
                binding.bbuttonGroupChecked1.setText("radio, button 1 checked")
            }
        }

        binding.bbuttonGroupChecked2.setOnCheckedChangedListener { _, isChecked ->
            if (isChecked) {
                binding.bbuttonGroupChecked2.setText("radio, button 2 checked")
            }
        }

        binding.bbuttonGroupChecked3.setOnCheckedChangedListener { _, isChecked ->
            if (isChecked) {
                binding.bbuttonGroupChecked3.setText("radio, button 3 checked")
            }
        }

        binding.setOnClickListener {
            when (it.id) {

                R.id.bbutton_group_orientation_change_btn -> {
                    val isHorizontal = binding.bbuttonGroupOrientationChange.getOrientation() == LinearLayout.HORIZONTAL
                    val newOrientation =
                        if (isHorizontal) LinearLayout.VERTICAL else LinearLayout.HORIZONTAL
                    binding.bbuttonGroupOrientationChange.setOrientation(newOrientation)
                }
                R.id.bbutton_group_outline_change_btn -> {
                    binding.bbuttonGroupOutlineChange.setShowOutline(!binding.bbuttonGroupOutlineChange.isShowOutline())

                }
                R.id.bbutton_group_rounded_change_btn -> {
                    binding.bbuttonGroupRoundedChange.setRounded(!binding.bbuttonGroupRoundedChange.isRounded())

                }
                R.id.bbutton_group_child_add_btn -> {
                    val count: Int = binding.bbuttonGroupChildChange.getChildCount()
                    val button = BootstrapButton(requireContext())
                    button.text = String.format("%d", count + 1)
                    binding.bbuttonGroupChildChange.addView(button)
                }
                R.id.bbutton_group_child_remove_btn -> {

                    val count: Int = binding.bbuttonGroupChildChange.getChildCount()

                    if (count > 0) {
                        binding.bbuttonGroupChildChange.removeViewAt(count - 1)
                    }
                }
                R.id.bbutton_group_brand_change_btn -> {

                    when (binding.bbuttonGroupBrandChange.getBootstrapBrand()) {
                        DefaultBootstrapBrand.PRIMARY -> binding.bbuttonGroupBrandChange.setBootstrapBrand(
                            DefaultBootstrapBrand.SUCCESS
                        )
                        DefaultBootstrapBrand.SUCCESS -> binding.bbuttonGroupBrandChange.setBootstrapBrand(
                            DefaultBootstrapBrand.INFO
                        )
                        DefaultBootstrapBrand.INFO -> binding.bbuttonGroupBrandChange.setBootstrapBrand(
                            DefaultBootstrapBrand.WARNING
                        )
                        DefaultBootstrapBrand.WARNING -> binding.bbuttonGroupBrandChange.setBootstrapBrand(
                            DefaultBootstrapBrand.DANGER
                        )
                        DefaultBootstrapBrand.DANGER -> binding.bbuttonGroupBrandChange.setBootstrapBrand(
                            DefaultBootstrapBrand.SECONDARY
                        )
                        DefaultBootstrapBrand.SECONDARY -> binding.bbuttonGroupBrandChange.setBootstrapBrand(
                            DefaultBootstrapBrand.REGULAR
                        )
                        DefaultBootstrapBrand.REGULAR -> binding.bbuttonGroupBrandChange.setBootstrapBrand(
                            DefaultBootstrapBrand.PRIMARY
                        )
                    }
                }
                R.id.bbutton_group_size_change_btn -> {

                    when (size) {
                        DefaultBootstrapSize.XS -> size = DefaultBootstrapSize.SM
                        DefaultBootstrapSize.SM -> size = DefaultBootstrapSize.MD
                        DefaultBootstrapSize.MD -> size = DefaultBootstrapSize.LG
                        DefaultBootstrapSize.LG -> size = DefaultBootstrapSize.XL
                        DefaultBootstrapSize.XL -> size = DefaultBootstrapSize.XS
                    }
                    binding.bbuttonGroupSizeChange.setBootstrapSize(size)
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