package com.kijlee.android.demo.entity.sql.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity.sql.room
 * @ClassName:      ChinaTownRoomDatabase
 * @Author:     kij
 * @Description:  中国城镇列表
 * @Date:    2024/2/15 20:16
 * @Version:    1.0
 */
@Database(entities = [ChinaCityRoom::class], version = 1, exportSchema = false)
abstract class ChinaTownRoomDatabase : RoomDatabase() {

    abstract fun chinaTownRoomDao(): ChinaTownRoomDao

    companion object {
        @Volatile
        private var INSTANCE: ChinaTownRoomDatabase? = null

        fun getDatabase(context: Context): ChinaTownRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChinaTownRoomDatabase::class.java,
                    "china_town"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}