package com.kijlee.android.demo.ui.sqllite.greendao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.kijlee.android.demo.App
import com.kijlee.android.demo.databinding.FgGreenDaoBinding
import com.kijlee.android.demo.entity.ChinaCity
import com.kijlee.android.demo.entity.ChinaTown
import com.kijlee.android.demo.ui.main.FgSqlLite
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import org.greenrobot.greendao.rx.RxDao
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.sqllite
 * @ClassName:      FgGreenDao
 * @Author:     kij
 * @Description:  GreenDao数据库用法
 * @Date:    2022/1/26 2:46 下午
 * @Version:    1.0
 */
class FgGreenDao : Fragment() {


    var _layoutBind: FgGreenDaoBinding? = null
    var item = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgSqlLite.SqlLite_Name)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgSqlLite.SqlLite_Name).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgGreenDaoBinding.inflate(layoutInflater)

        val root: View = binding.root
        val city_json_io = requireContext().assets.open("json/china_town.json")
        var stringBuilder = StringBuilder()
        var bufferReader = BufferedReader(InputStreamReader(city_json_io, "utf-8"))
        while (true) {
            val line = bufferReader.readLine()
            if (line == null) {
                break
            } else {
                stringBuilder.append(line).append("\n")
            }
        }
        city_json_io.close()
        var citys = JSONArray(stringBuilder.toString())
        // 新增数据
//        addProvince(citys)
        // 查询数据
        selectProvice()


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }

    //查询省份
    fun selectProvice(){

        val rxDao = (requireActivity().application as App).getDaoSession()
        var provinces :MutableList<ChinaCity> = rxDao.loadAll<ChinaCity,ChinaCity>(ChinaCity::class.java)
        var stringBuilder = StringBuilder()
        for (item in provinces){
            stringBuilder.append(item.name).append("\n")
        }
            binding.sqlName = "$item\n$stringBuilder"
    }

    //添加城市
    fun addCity(){

    }

    // 添加省份
    fun addProvince(citys:JSONArray){

        val rxDao = (requireActivity().application as App).getDaoSession()

        rxDao.runInTx {
            for (i in 0 until citys.length()) {
                val city = Gson().fromJson(citys[i].toString(), ChinaTown::class.java)
                var chinaCity = ChinaCity()
                chinaCity._id = i.toLong()
                chinaCity.code = city.code
                chinaCity.name = city.name
                chinaCity.url = city.url
                Logger.e("chinaCity.name-----${chinaCity.name}")
                rxDao.insert(chinaCity)
            }
        }
    }

}