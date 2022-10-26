package com.kijlee.android.demo.utils

import android.content.Context
import android.os.CountDownTimer
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import java.util.concurrent.TimeUnit

/**
 * @ProjectName:    主干代码
 * @Package:        com.sphd.kij.systemmanger.utils
 * @ClassName:      Smg
 * @Author:     kij
 * @Description:  显示信息
 * @Date:    2022/10/26 14:20
 * @Version:    1.0
 */
class Smg {
companion object{

    fun showQMUITipDialog(context: Context, strTipWord:String, iconType:Int?, seconds:Long?){

        val tipDialog =   QMUITipDialog.Builder(context)
            .setIconType(iconType?: QMUITipDialog.Builder.ICON_TYPE_INFO)
            .setTipWord(strTipWord)
            .create();
        tipDialog!!.show()
        val showdialog = object :
            CountDownTimer(TimeUnit.SECONDS.toMillis(seconds?:3), 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                tipDialog.dismiss()
            }
        }
        showdialog!!.start()
    }

}
}