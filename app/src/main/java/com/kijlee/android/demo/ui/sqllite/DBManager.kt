package com.kijlee.android.demo.ui.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import com.kijlee.android.demo.entity.sql.litepal.ChinaTownLitePal
import com.orhanobut.logger.Logger
import org.litepal.LitePal
import java.io.*

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
                inputStream = mContext!!.getResources().getAssets().open(DB_NAME_V1)
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


    fun copyDataToLitepal(){
        val db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null)
        val cursor = db.rawQuery("select * from CHINA_CITY", null)
        val result: MutableList<ChinaTownLitePal> = ArrayList<ChinaTownLitePal>()
        var city: ChinaTownLitePal
        while (cursor.moveToNext()) {
            var code = cursor.getString(cursor.getColumnIndexOrThrow("CODE"))
            var name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"))
            var city_id = cursor.getLong(cursor.getColumnIndexOrThrow("CITY_ID"))
            var county_id = cursor.getLong(cursor.getColumnIndexOrThrow("COUNTY_ID"))
            var town_id = cursor.getLong(cursor.getColumnIndexOrThrow("TOWN_ID"))
            city = ChinaTownLitePal(code, name, city_id, county_id,town_id)
            result.add(city)
        }
        LitePal.saveAll(result)
        cursor.close()
        db.close()
    }

}