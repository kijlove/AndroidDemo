package com.kijlee.android.demo.ui.sqllite.objectbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.kijlee.android.demo.databinding.FgObjectBoxBinding
import com.kijlee.android.demo.entity.ChinaTown
import com.kijlee.android.demo.entity.ChinaTown_
import com.kijlee.android.demo.entity.objectbox.ObjectBox
import com.kijlee.android.demo.ui.cityselect.CityListAdapter
import com.kijlee.android.demo.ui.main.FgSqlLite
import com.orhanobut.logger.Logger
import io.objectbox.Box
import io.objectbox.query.Query
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.sqllite.objectbox
 * @ClassName:      FgObjectBox
 * @Author:     kij
 * @Description:  ObjectBox数据库练习
 * @Date:    2022/10/22 22:17
 * @Version:    1.0
 */
class FgObjectBox : Fragment() {


    var _layoutBind: FgObjectBoxBinding? = null
    var item = ""
    var adapter: CityListAdapter? = null

    private var chinaBox: Box<ChinaTown>? = null
    private var chinaQuery: Query<ChinaTown>? = null

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

        _layoutBind = FgObjectBoxBinding.inflate(layoutInflater)
        chinaBox = ObjectBox.boxStore.boxFor(ChinaTown::class.java)
        chinaQuery = chinaBox!!.query().equal(ChinaTown_.city_id,11).order(ChinaTown_.code).build();
        adapter = CityListAdapter()

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
        binding.insert.setOnClickListener{
            addProvince(citys)
        }

        // 查询数据
        binding.select.setOnClickListener{
            val provinces = selectProvice()
            chinaQuery!!.close()
            binding.recyclerView.adapter =  adapter
            adapter!!.setList(provinces)
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }

    fun addProvince(citys: JSONArray) {
        val chinaProvinces: MutableList<ChinaTown> = ArrayList()
        for (i in 0 until citys.length()) {
            // 省
            val provinces = Gson().fromJson(citys[i].toString(), ChinaTown::class.java)
            chinaProvinces.add(provinces)
        }
        // 全国的城市
        val chinaCitys: MutableList<ChinaTown> = ArrayList()
        for (province in chinaProvinces) {
            for (city in province.city!!){
                city.city_id = province.code!!.toLong()
                chinaCitys.add(city)
            }
        }

        // 县
        val chinaCountys: MutableList<ChinaTown> = ArrayList()
        for (city in chinaCitys){
            for(county in city.county!!){
                county.city_id = city.city_id
                county.county_id = city.code!!.toLong()
                chinaCountys.add(county)
            }
        }
        // 乡镇
        val chinaTowns: MutableList<ChinaTown> = ArrayList()
        for(county in chinaCountys){
            for(town in county.town!!){
                town.city_id = county.city_id
                town.county_id = county.county_id
                town.town_id = county.code!!.toLong()
                Logger.e(town.name!!)
                chinaTowns.add(town)
            }
        }

        //建立一个链接
        chinaBox!!.put(chinaProvinces) // 省
        chinaBox!!.put(chinaCitys)     // 市
        chinaBox!!.put(chinaCountys)   // 县
        chinaBox!!.put(chinaTowns)     // 乡镇

    }


    //查询省份
    fun selectProvice(): MutableList<ChinaTown> {
        return        chinaQuery!!.find()


    }


}