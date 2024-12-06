package com.kijlee.android.demo.entity.objectbox

import android.content.Context
import androidx.multidex.BuildConfig
import com.kijlee.android.demo.entity.MyObjectBox
import com.orhanobut.logger.Logger
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

object ObjectBox {
    lateinit var boxStore: BoxStore
        private set


    fun init(context: Context): BoxStore {
        if (ObjectBox::boxStore.isInitialized && !boxStore.isClosed) {
            return boxStore
        }

        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()


        if (BuildConfig.DEBUG) {
            Logger.e( String.format("Using ObjectBox %s (%s)",
                BoxStore.getVersion(), BoxStore.getVersionNative()));
            // Enable ObjectBox Admin on debug builds.
            // https://docs.objectbox.io/data-browser

            // 开启一个浏览服务
            val started = AndroidObjectBrowser(boxStore).start(context)
        }

        return boxStore
    }
}