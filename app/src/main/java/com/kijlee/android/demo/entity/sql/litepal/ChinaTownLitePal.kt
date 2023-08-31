package com.kijlee.android.demo.entity.sql.litepal

import org.litepal.crud.LitePalSupport

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity.sql.litepal
 * @ClassName:      ChinaTownLitePal
 * @Author:     kij
 * @Description:  litepal框架下的数据代码
 * @Date:    2022/12/2 09:51
 * @Version:    1.0
 */
class ChinaTownLitePal constructor(code:String?,name:String?,city_id: Long?,county_id: Long?,town_id: Long?): LitePalSupport() {

    var _id: Long = 0
    var code: String? = null
    var name: String? = null
    var city_id: Long? = 0
    var county_id: Long? = 0
    var town_id: Long? = 0
    constructor() :this("","",0,0,0)
    init {
        this.code = code
        this.name = name
        this.city_id = city_id
        this.county_id = county_id
        this.town_id = town_id
    }

    override fun toString(): String {
        return this.name.toString()
    }
}
