package com.kijlee.android.demo.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.kijlee.android.demo.databinding.ActivityMainBinding
import com.orhanobut.logger.Logger
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    //检查权限
    private val PERMISSIONS_REQUEST_READ_CONTACTS = 8
    var _binding: ActivityMainBinding? = null

    private val binding get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestPermissions()
        }


    }


    //请求权限sign_norm_activity
    fun requestPermissions() {
        XXPermissions.with(this)
            // 申请单个权限
            .permission(Permission.CAMERA)
            // 申请多个权限
//            .permission(Permission.Group.CALENDAR)
            // 设置权限请求拦截器（局部设置）
            //.interceptor(new PermissionInterceptor())
            // 设置不触发错误检测机制（局部设置）
            //.unchecked()
            .request(object : OnPermissionCallback {

                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        Logger.e("获取部分权限成功，但部分权限未正常授予")
                        return
                    }
                    Logger.e("获取录音和日历权限成功")
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        Logger.e("被永久拒绝授权，请手动授予录音和日历权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(this@MainActivity, permissions)
                    } else {
                        Logger.e("获取录音和日历权限失败")
                    }
                }
            })
    }

    //请求权限的回调
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var hasPermissionDismiss = false
        if (PERMISSIONS_REQUEST_READ_CONTACTS == requestCode) {
            for (i in grantResults) {
                if (i == -1) {
                    hasPermissionDismiss = true
                    break
                }
            }
        }
        if (hasPermissionDismiss) {
            showPermissionsDialog()
        }
    }

    //弹出提示框区设置权限
    fun showPermissionsDialog() {

        QMUIDialog.MessageDialogBuilder(this@MainActivity)
            .setTitle("提示")
            .setMessage("已禁用权限,请手动赋予")
            .addAction(0, "设置", QMUIDialogAction.ACTION_PROP_POSITIVE) { dialog, index ->
                dialog.dismiss()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:$packageName")
                startActivity(intent)
            }
            .addAction(0, "取消", QMUIDialogAction.ACTION_PROP_NEGATIVE) { dialog, index ->
                dialog.dismiss()
            }
            .create().show()
    }

}


