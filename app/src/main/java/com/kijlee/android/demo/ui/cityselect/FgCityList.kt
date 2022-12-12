package com.kijlee.android.demo.ui.cityselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FgCityListBinding
import com.kijlee.android.demo.entity.ChinaTown
import com.kijlee.android.demo.entity.ChinaTown_
import com.kijlee.android.demo.entity.objectbox.ObjectBox
import com.kijlee.android.demo.ui.main.FgTabLayout
import com.orhanobut.logger.Logger
import io.objectbox.Box
import io.objectbox.query.Query

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.cityselect
 * @ClassName:      FgCityList
 * @Author:     kij
 * @Description:  城市列表
 * @Date:    2022/10/19 19:44
 * @Version:    1.0
 */
class FgCityList : Fragment() {


    var _layoutBind: FgCityListBinding? = null
    var item = ""
    var adapter: CityListAdapter? = null
    var setCityTabImp: SetCityTabImp? = null


    private val binding get() = _layoutBind!!
    var cityId = 11L
        set(value) {
            Logger.e("value=======$value")
            if(value!=null){
                field = value
                chinaBox = ObjectBox.boxStore.boxFor(ChinaTown::class.java)
                chinaQuery = chinaBox!!.query().equal(ChinaTown_.city_id,field).equal(ChinaTown_.county_id,0).order(ChinaTown_.code).build();
                if(adapter!=null){
                adapter!!.setList(selectProviceObjectBox())
                }

            }else{
                field=0
            }

        }

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
        chinaQuery = chinaBox!!.query().equal(ChinaTown_.city_id,cityId).equal(ChinaTown_.county_id,0).order(ChinaTown_.code).build();
        adapter = CityListAdapter()


        val root: View = binding.root
//        binding.name = item
        binding.recycler.adapter =  adapter
        adapter!!.setList(selectProviceObjectBox())
//        selectProvice()
        onClick()
        return root
    }


    //查询省份
    fun selectProviceObjectBox(): MutableList<ChinaTown> {
        return chinaQuery!!.find()
    }


    fun onClick(){

        adapter!!.setOnItemClickListener { adapter, view, position ->

            val itemProvince = adapter.getItem(position)  as ChinaTown
            setCityTabImp!!.setTabText(itemProvince)
        }


    }
}