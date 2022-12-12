package com.kijlee.android.demo.ui.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.orhanobut.logger.Logger
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.workmanager
 * @ClassName:      RequestWorker
 * @Author:     kij
 * @Description:  测试workmanager
 * @Date:    2022/11/4 11:50
 * @Version:    1.0
 */
class RequestWorker (context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        //获取OneTimeWorkManager设置的数据(是否进行网络请求)
        val isRequest = this.inputData.getBoolean("isRequest",false)
        var responseString = "网络请求失败"
        lateinit var outputData : Data
        if (isRequest){
            System.out.println("睡眠之前-------------"+Thread.currentThread())
            //担心网络请求响应过快,所以先睡会在请求
            Thread.sleep(5000)
            System.out.println("睡眠后请求-------------"+Thread.currentThread())
            //创建协程作用域执行网络请求，runBlocking会阻塞当前线程直到结果完成
            runBlocking {
                responseString =  retrofitRequest()
                //responseString+"${System.currentTimeMillis()}"
                Logger.e("responseString${responseString}")
                outputData = Data.Builder().putString("data",responseString).build()
            }
            return Result.success(outputData)
        }
        outputData = Data.Builder().putString("data",responseString).build()
        return Result.failure(outputData)
    }

    /**
     * Retrofit执行网络请求
     */
    private  suspend fun retrofitRequest() : String {
        val retrofit = Retrofit.Builder().baseUrl(WorkManagerActivity.baseUrl)
            .build()
        var apiService =  retrofit.create(ApiService ::class.java)

        val requestData : Response<ResponseBody> = apiService.requestApi()
        val jsonString = String((requestData.body())?.bytes()!!)
        return  jsonString
    }
}