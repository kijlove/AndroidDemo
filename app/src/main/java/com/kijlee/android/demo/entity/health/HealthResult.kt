package com.kijlee.android.demo.entity.health

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity.health
 * @ClassName:      HealthResult
 * @Author:     kij
 * @Description:  健康界数据接收
 * @Date:    2022/3/13 10:24 上午
 * @Version:    1.0
 */
class HealthResult<T>(){

    var count: Int = 0
    var data: HealthData<T>? = null
}

class HealthData<T>(){
    var datalist: MutableList<T> = ArrayList()
    var wmCount: Int = 0
    var zxCount: Int = 0
}