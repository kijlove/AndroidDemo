package com.kijlee.android.demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kijlee.android.demo.ui.main.MainActivity

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo
 * @ClassName:      BootReceiver
 * @Author:     kij
 * @Description:  启动程序
 * @Date:    2025/12/29 21:35
 * @Version:    1.0
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // 判断是否为开机广播
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {

            // 启动你的主 Activity
            val i = Intent(context, MainActivity::class.java).apply {
                // Activity 必须在新的任务栈中启动
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                // 如果你想让应用启动得更平滑，可以加一个延迟或判断
            }
            context.startActivity(i)
        }
    }
}