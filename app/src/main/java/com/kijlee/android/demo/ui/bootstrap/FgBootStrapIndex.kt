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
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.hjq.permissions.permission.PermissionLists
import com.hjq.permissions.permission.base.IPermission
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgBootStrapIndexBinding
import com.orhanobut.logger.Logger

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
                R.id.request_qx -> {
                    XXPermissions.with(this)
                        // 申请单个权限
                        .permission(PermissionLists.getCameraPermission())
                        // 申请多个权限
//            .permission(Permission.Group.CALENDAR)
                        // 设置权限请求拦截器（局部设置）
                        //.interceptor(new PermissionInterceptor())
                        // 设置不触发错误检测机制（局部设置）
                        //.unchecked()
                        .request(object : OnPermissionCallback {

                            override fun onResult(
                                grantedList: List<IPermission?>,
                                deniedList: List<IPermission?>
                            ) {
                                val allGranted = deniedList.isEmpty()
                                if (!allGranted) {
                                    Logger.e("获取部分权限成功，但部分权限未正常授予")
                                    return
                                }
                                Logger.e("获取相机")

                            }
                        })

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