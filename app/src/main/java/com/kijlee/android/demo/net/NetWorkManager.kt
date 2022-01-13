package com.kijlee.android.demo.net

import com.kijlee.android.demo.net.Api.Companion.APP_DEFAULT_DOMAIN
import com.kijlee.android.demo.net.Api.Companion.APP_Luffy_City
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.net
 * @ClassName:      NetWorkManager
 * @Author:     kij
 * @Description:  配置网络请求
 * @Date:    2022/1/13 9:17 下午
 * @Version:    1.0
 */
class NetWorkManager private constructor(){
    var mOkHttpClient: OkHttpClient
    var mRetrofit: Retrofit
    //    路飞接口
    var mLuffyCityService: LuffyCityService

    private object NetWorkManagerHolder {
        val INSTANCE = NetWorkManager()
    }

    //单例模式
    init {
        this.mOkHttpClient =
            RetrofitUrlManager.getInstance().with(OkHttpClient.Builder()) //RetrofitUrlManager 初始化
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
        this.mRetrofit = Retrofit.Builder()
            .baseUrl(APP_Luffy_City)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //使用rxjava
            .addConverterFactory(GsonConverterFactory.create()) //使用Gson
            .client(mOkHttpClient)
            .build()
        this.mLuffyCityService = mRetrofit.create(LuffyCityService::class.java)
    }

    companion object {
        val instance: NetWorkManager by lazy { NetWorkManagerHolder.INSTANCE }
    }
}