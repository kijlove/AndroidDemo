package com.kijlee.android.demo.entity.sql.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity.sql.room
 * @ClassName:      ChinaTownRoomDao
 * @Author:     kij
 * @Description:  城市列表查询
 * @Date:    2024/2/15 19:00
 * @Version:    1.0
 */
@Dao
interface ChinaTownRoomDao {

    @Query("SELECT * from CHINA_CITY ORDER BY CODE ASC")
    fun getCitys(): Flow<List<ChinaCityRoom>>

    @Query("SELECT * from CHINA_CITY WHERE _id = :id")
    fun getCity(id: Int): Flow<ChinaCityRoom>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ChinaCityRoom)

    @Update
    suspend fun update(item: ChinaCityRoom)

    @Delete
    suspend fun delete(item: ChinaCityRoom)
}