package com.kijlee.android.demo.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import org.greenrobot.greendao.annotation.NotNull
import org.greenrobot.greendao.annotation.ToMany

import java.io.Serializable

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity
 * @ClassName:      ChinaTown
 * @Author:     kij
 * @Description:  中国城镇
 * @Date:    2022/1/27 6:24 下午
 * @Version:    1.0
 * schema：告知GreenDao当前实体属于哪个schema
 * active：标记一个实体处于活跃状态，活动实体有更新、删除和刷新方法
 * nameInDb：在数据库中使用的别名，默认使用的是实体的类名
 * indexes：定义索引，可以跨越多个列
 * createInDb：标记创建数据库表
 *  object box
 * @Entity：对象持久化；
 * @Id：这个对象的主键,默认情况下，id是会被objectbox管理的，也就是自增id。手动管理id需要在注解的时候加上@Id(assignable = true)。当你在自己管理id的时候如果超过long的最大值，objectbox 会报错；id的值不能为负数；当id等于0时objectbox会认为这是一个新的实体对象,因此会新增到数据库表中；
 * @Index：这个对象中的索引。经常大量进行查询的字段创建索引，用于提高查询性能；
 * @Transient：某个字段不想被持久化，可以使用此注解，字段将不会保存到数据库；
 * @NameInDb：数据库中的字段自定义命名；
 * @ToOne：做一对一的关联注解 ，此外还有一对多，多对多的关联，例如Class的示例；
 * @ToMany：做一对多的关联注解；
 * @Backlink：表示反向关联。
 *
 */
@Entity
data class ChinaTown(

    @Id
    var id: Long = 0,
    @NotNull
    val code: String? = null,
    @NotNull
    val name: String? = null,
    val url: String? = null,
    var city_id: Long = 0,
    var county_id: Long = 0,
    var town_id: Long = 0,

    @ToMany(referencedJoinProperty = "city_id")
    val city: MutableList<ChinaTown>? = null,
    @ToMany(referencedJoinProperty = "county_id")
    val county: MutableList<ChinaTown>? = null,
    @ToMany(referencedJoinProperty = "town_id")
    val town: MutableList<ChinaTown>? = null
) : Serializable
