package com.kijlee.android.demo.ui.cityselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.kijlee.android.demo.App
import com.kijlee.android.demo.entity.ChinaCity
import com.kijlee.android.demo.entity.ChinaTown
import com.kijlee.android.demo.entity.ChinaTown_
import com.kijlee.android.demo.entity.objectbox.ObjectBox
import com.kijlee.android.demo.ui.main.FgTabLayout
import com.kijlee.android.demo.utils.Smg
import com.kijlee.android.demo.databinding.FgCityListBinding
import com.orhanobut.logger.Logger
import io.objectbox.Box
import io.objectbox.query.Query
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.cityselect
 * @ClassName:      FgProvinceList
 * @Author:     kij
 * @Description:  省列表
 * @Date:    2022/10/26 19:26
 * @Version:    1.0
 */
class FgProvinceList : Fragment() {


    var _layoutBind: FgCityListBinding? = null
    var item = ""
    var adapter: CityListAdapter? = null
    var setCityTabImp: SetCityTabImp? = null


    private val binding get() = _layoutBind!!

    private var chinaBox: Box<ChinaTown>? = null
    private var chinaQuery: Query<ChinaTown>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgTabLayout.Tab_Name)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString((FgTabLayout.Tab_Name)).toString()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgCityListBinding.inflate(layoutInflater)
        chinaBox = ObjectBox.boxStore.boxFor(ChinaTown::class.java)
        chinaQuery = chinaBox!!.query().equal(ChinaTown_.city_id,0).order(ChinaTown_.code).build();
        adapter = CityListAdapter()


        val root: View = binding.root
//        binding.name = item
        binding.recycler.adapter =  adapter
        adapter!!.setList(selectProviceObjectBox())
//        selectProvice()
        onClick()
        return root
    }

    // 通过json文件获取
    fun getCitysByJson(): MutableList<ChinaTown> {

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
        val citys = JSONArray(stringBuilder.toString())
        val cityList :MutableList<ChinaTown> = ArrayList()
        for (i in 0 until citys.length()) {
            val provinces = Gson().fromJson(citys[i].toString(), ChinaTown::class.java)
            // 添加城市
            cityList.add(provinces)
        }
        return cityList
    }

    //查询省份 green_dao
    fun selectProvice() :MutableList<ChinaCity>{
        val rxDao = (requireActivity().application as App).getDaoSession()
        var provinces :MutableList<ChinaCity> = rxDao.loadAll<ChinaCity, ChinaCity>(ChinaCity::class.java)
        var stringBuilder = StringBuilder()
        for (item in provinces){
            stringBuilder.append(item.name).append("\n")
        }

        Logger.e(stringBuilder.toString())

        return provinces
    }


    //查询省份
    fun selectProviceObjectBox(): MutableList<ChinaTown> {
        return chinaQuery!!.find()
    }


    fun onClick(){

        adapter!!.setOnItemClickListener { adapter, view, position ->
            Logger.e("parentFragmentManager---------${parentFragmentManager.fragments[0].parentFragmentManager.fragments[1]::class.java.name}")
            val itemProvince = adapter.getItem(position)  as ChinaTown
//            Smg.showQMUITipDialog(requireContext(),itemProvince.name!!,null,null)

            setCityTabImp!!.setTabText(itemProvince)
        }


    }
}