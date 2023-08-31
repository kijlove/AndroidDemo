package com.kijlee.android.demo

import androidx.multidex.MultiDexApplication
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.kijlee.android.demo.entity.greendao.DaoMaster
import com.kijlee.android.demo.entity.greendao.DaoSession

import com.kijlee.android.demo.net.Api.Companion.HEALTH_NAME
import com.kijlee.android.demo.net.Api.Companion.HEALTH_URL_DOMAIN
import com.kijlee.android.demo.entity.objectbox.ObjectBox
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import org.litepal.LitePal


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo
 * @ClassName:      App
 * @Author:     kij
 * @Description:  Application
 * @Date:    2022/1/10 9:21 上午
 * @Version:    1.0
 */
class App: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        // QMUI设置
        QMUISwipeBackActivityManager.init(this)
        // setup default typefaces 字体
        TypefaceProvider.registerDefaultIconSets()
        // greendao数据库
//        initDao()

        //日志打印
        Logger.addLogAdapter(AndroidLogAdapter())
        //切换不同的 BaseUrl
        RetrofitUrlManager.getInstance().setDebug(true)
        //将每个 BaseUrl 进行初始化,运行时可以随时改变 DOMAIN_NAME 对应的值,从而达到切换 BaseUrl 的效果
        RetrofitUrlManager.getInstance().putDomain(HEALTH_NAME, HEALTH_URL_DOMAIN)
//        ObjectBox.init(this)
        LitePal.initialize(this);
    }

    // 具体类 需要makebuild生成
    fun initDao() {
        val helper = DaoMaster.DevOpenHelper(this, "china_town")
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()
    }

        private var daoSession: DaoSession? = null
        open fun getDaoSession(): DaoSession {
            return daoSession!!
        }
    companion object {
        const val TAG = "OBXSync"
    }
}