package com.kijlee.android.demo.net

import com.kijlee.android.demo.entity.health.HealthResult
import com.kijlee.android.demo.entity.health.NewsBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.net
 * @ClassName:      HealthService
 * @Author:     kij
 * @Description:  健康界
 * @Date:    2022/3/12 9:53 下午
 * @Version:    1.0
 */
interface HealthService {

    //https://www.cn-healthcare.com/api/article/articlelist?data={"start":"5","size":"30","arctype":"0","wmstart":"9","flag":"2"}
    @GET("api/article/articlelist?")
    fun articlelist(@Query("data")data:String): Observable<Response<HealthResult<NewsBean>>>
}