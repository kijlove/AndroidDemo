package com.kijlee.android.demo.net

import java.util.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.net
 * @ClassName:      LuffyCityService
 * @Author:     kij
 * @Description:  路飞学成接口测试
 * @Date:    2022/1/13 9:28 下午
 * @Version:    1.0
 */
interface LuffyCityService {

    //https://api.luffycity.com/api/v1/course/actual/?category_id=2&offset=0&limit=5
    @GET("api/v1/course/actual/?")
    fun actual(@Query("category_id")category_id:Int,
                       @Query("offset")offset:Int,
                       @Query("limit")limit:Int): Observable<Response<ResponseBody>>
}