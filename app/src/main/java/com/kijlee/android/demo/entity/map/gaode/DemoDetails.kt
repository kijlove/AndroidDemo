package com.kijlee.android.demo.entity.map.gaode

import android.app.Activity
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity.map.gaode
 * @ClassName:      DemoDetails
 * @Author:     kij
 * @Description:  高德地图中目录
 * @Date:    2022/3/10 10:12 下午
 * @Version:    1.0
 */
class DemoDetails constructor(
    titleId: Int, descriptionId: Int,
    activityClass: Class<out Activity?>?): MultiItemEntity {
    constructor():this(0,0,null)
    var type = 0
    var titleId = 0
    var descriptionId = 0
    var activityClass: Class<out Activity?>? = null

    init {
        this.titleId = titleId
        this.descriptionId = descriptionId
        this.activityClass = activityClass
        this.type = if (activityClass==null) 0 else 1
    }

    override val itemType: Int
        get() = type



}