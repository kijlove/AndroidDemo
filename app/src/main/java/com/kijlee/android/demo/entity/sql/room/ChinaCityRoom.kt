package com.kijlee.android.demo.entity.sql.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity.sql.room
 * @ClassName:      ChinaTownRoom
 * @Author:     kij
 * @Description:  城市列表room数据库
 * @Date:    2024/2/15 18:54
 * @Version:    1.0
 */

@Entity(tableName = "CHINA_CITY")
data class ChinaCityRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0,
    @ColumnInfo(name = "CODE")
    val code: String,
    @ColumnInfo(name = "NAME")
    val name: String,
    @ColumnInfo(name = "CITY_ID")
    val cityId: Int,
    @ColumnInfo(name = "COUNTY_ID")
    val countyId: Int,
    @ColumnInfo(name = "TOWN_ID")
    val townId: Int

)