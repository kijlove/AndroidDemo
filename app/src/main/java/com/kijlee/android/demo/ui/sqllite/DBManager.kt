package com.kijlee.android.demo.ui.sqllite

//import org.litepal.LitePal
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import com.kijlee.android.demo.entity.sql.room.ChinaCityRoom
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.sqllite
 * @ClassName:      DBManager
 * @Author:     kij
 * @Description:  将asset文件中数据库写入到db文件夹
 * @Date:    2022/12/1 22:40
 * @Version:    1.0
 */
class DBManager constructor(context: Context) {
    private var DB_PATH: String? = null
    private var mContext: Context? = null
    private val BUFFER_SIZE = 1024

    val DB_NAME_V1 = "db/china_town.db"

    val LATEST_DB_NAME = "china_city.db"

    init {

        this.mContext = context
        DB_PATH = (File.separator + "data"
                + Environment.getDataDirectory().absolutePath + File.separator
                + context.packageName + File.separator + "databases" + File.separator)
        copyDBFile()

    }

    private fun copyDBFile() {
        val dir: File = File(DB_PATH)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        //创建新版本数据库
        val dbFile: File = File(DB_PATH + LATEST_DB_NAME)

        if (!dbFile.exists()) {
            val inputStream: InputStream
            val os: OutputStream
            try {
                inputStream = mContext!!.resources.assets.open(DB_NAME_V1)
                os = FileOutputStream(dbFile)
                val buffer = ByteArray(BUFFER_SIZE)
                var length: Int
                while (inputStream.read(buffer, 0, buffer.size).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
                os.close()
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun copyDataToLitepal():MutableList<ChinaCityRoom> {
        val db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null)
        val cursor = db.rawQuery("select * from CHINA_CITY", null)
        val result: MutableList<ChinaCityRoom> = ArrayList<ChinaCityRoom>()
        var city: ChinaCityRoom
        while (cursor.moveToNext()) {
            val code = cursor.getString(cursor.getColumnIndexOrThrow("CODE"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"))
            val city_id = cursor.getInt(cursor.getColumnIndexOrThrow("CITY_ID"))
            val county_id = cursor.getInt(cursor.getColumnIndexOrThrow("COUNTY_ID"))
            val town_id = cursor.getInt(cursor.getColumnIndexOrThrow("TOWN_ID"))
            city = ChinaCityRoom(code = code, name = name, cityId = city_id, countyId = county_id, townId = town_id)
            result.add(city)

        }
//        LitePal.saveAll(result)
        cursor.close()
        db.close()
        return result
    }

}