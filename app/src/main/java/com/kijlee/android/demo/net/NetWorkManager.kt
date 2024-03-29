package com.kijlee.android.demo.net

import com.orhanobut.logger.Logger
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    //    健康界网站中破解出来的接口
    var mhealthService: HealthService

    private object NetWorkManagerHolder {
        val INSTANCE = NetWorkManager()
    }

    //单例模式
    init {

        val httpClient = OkHttpClient.Builder()
        //添加拦截
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Logger.v(message)
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY;
        this.mOkHttpClient =
            RetrofitUrlManager.getInstance().with(httpClient) //RetrofitUrlManager 初始化
                .addInterceptor(loggingInterceptor)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
        this.mRetrofit = Retrofit.Builder()
            .baseUrl(Api.HEALTH_URL_DOMAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //使用rxjava
            .addConverterFactory(GsonConverterFactory.create()) //使用Gson
            .client(mOkHttpClient)
            .build()
        this.mLuffyCityService = mRetrofit.create(LuffyCityService::class.java)
        this.mhealthService = mRetrofit.create(HealthService::class.java)
    }

    companion object {
        val instance get() = NetWorkManagerHolder.INSTANCE
    }
}