package com.kijlee.android.demo
import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo
 * @ClassName:      MyDeviceAdminReceiver
 * @Author:     kij
 * @Description:  自启动
 * @Date:    2025/12/29 21:57
 * @Version:    1.0
 */

class MyDeviceAdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Toast.makeText(context, "设备管理员权限已启用", Toast.LENGTH_SHORT).show()
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Toast.makeText(context, "设备管理员权限已禁用", Toast.LENGTH_SHORT).show()
    }
}