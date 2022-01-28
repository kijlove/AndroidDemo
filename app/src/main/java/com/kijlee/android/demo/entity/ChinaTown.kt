package com.kijlee.android.demo.entity
import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.NotNull
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

 * @Id：主键 Long 型，可以通过@Id(autoincrement = true)设置自增长
 * @Property：设置一个非默认关系映射所对应的列名，默认是使用字段名，例如：@Property(nameInDb = "name")
 * @NotNull：设置数据库表当前列不能为空
 * @Transient：添加此标记后不会生成数据库表的列
 * @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
 * @Unique：向数据库添加了一个唯一的约束
 * @ToOne：定义与另一个实体（一个实体对象）的关系
 * @ToMany：定义与多个实体对象的关系
 *
 */
data class ChinaTown(

    val code: String,
    val name: String,
    val url: String,
    var city:MutableList<ChinaTown>,
    var county:MutableList<ChinaTown>,
    var town:MutableList<ChinaTown>
):Serializable
