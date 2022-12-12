package com.kijlee.android.demo.ui.workmanager

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

/**
Autour: zjt
Date: 2021-01-17
Description:
 **/
interface ApiService {
    //用协程的话就不能是Call而是Response
    @GET("api/free/random/get")
    suspend  fun requestApi() : Response<ResponseBody>


}