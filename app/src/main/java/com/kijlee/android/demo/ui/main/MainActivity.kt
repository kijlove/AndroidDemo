package com.kijlee.android.demo.ui.main

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.hjq.permissions.permission.PermissionLists
import com.hjq.permissions.permission.base.IPermission
import com.kijlee.android.demo.MyDeviceAdminReceiver
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
    private lateinit var dpm: DevicePolicyManager
    private lateinit var adminName: ComponentName


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestPermissions()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            startActivity(intent)
        }

        dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        adminName = ComponentName(this, MyDeviceAdminReceiver::class.java)
        // 尝试开启 Kiosk 模式
        setupKioskMode()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupKioskMode() {
        // 检查应用是否已经是 Device Owner (通过 ADB 激活)
        if (dpm.isDeviceOwnerApp(packageName)) {
            // 允许当前应用进入锁定模式
            dpm.setLockTaskPackages(adminName, arrayOf(packageName))

            // 启动锁定 (如果已经设置了包名，此方法会直接锁定且没有确认弹窗)
            startLockTask()

            // 可选：禁用状态栏、通知栏、电源键菜单等（仅限 Android 9.0+）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                dpm.setLockTaskFeatures(adminName,
                    DevicePolicyManager.LOCK_TASK_FEATURE_NONE // 彻底禁用一切
                )
            }
        } else {
            // 如果不是 Device Owner，则调用普通 Pinning (用户可以手动退出)
            startLockTask()
        }
    }

    //请求权限sign_norm_activity
    fun requestPermissions() {
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


