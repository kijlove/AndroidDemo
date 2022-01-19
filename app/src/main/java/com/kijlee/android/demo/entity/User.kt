package com.kijlee.android.demo.entity

import java.io.Serializable

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity
 * @ClassName:      User
 * @Author:     kij
 * @Description:  测试用户类
 * @Date:    2022/1/4 4:35 下午
 * @Version:    1.0
 */
class User :Serializable{
    var name: String?=null
    var password: String?=null
    var logonName:String? =null
}