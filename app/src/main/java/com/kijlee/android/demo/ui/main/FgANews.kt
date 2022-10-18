package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.kijlee.android.demo.databinding.FgTab1ListBinding
import com.kijlee.android.demo.entity.health.NewsBean
import com.kijlee.android.demo.net.Api
import com.kijlee.android.demo.net.NetWorkManager
import com.kijlee.android.demo.ui.tablayout.HealthAdapter
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgANews
 * @Author:     kij
 * @Description:  测试一个新闻列表显示
 * @Date:    2022/3/12 9:49 下午
 * @Version:    1.0
 */
class FgANews : Fragment() {


    var _layoutBind: FgTab1ListBinding? = null
    var item = ""
    var adapter: HealthAdapter? = null

    private val binding get() = _layoutBind!!


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

        _layoutBind = FgTab1ListBinding.inflate(layoutInflater)

        var clickEntityList: MutableList<NewsBean>? = ArrayList()
        adapter = HealthAdapter(clickEntityList!!)

        val root: View = binding.root
        binding.name = item

        binding.recycler.adapter =  adapter

        val httpUrl = RetrofitUrlManager.getInstance().fetchDomain(Api.HEALTH_NAME)
        Logger.e(httpUrl.toString())

        if (httpUrl == null || httpUrl.toString() != Api.HEALTH_URL_DOMAIN
                .toString()
        ) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            RetrofitUrlManager.getInstance().putDomain(Api.HEALTH_NAME, Api.HEALTH_URL_DOMAIN)
        }
//        {"start":"11","size":"30","arctype":"0","wmstart":"33","flag":"2"}
        val dataPage = Gson().toJson(HealPage("11","30","0","33","2"))
        Logger.e(dataPage)

        NetWorkManager.instance.mhealthService.articlelist(dataPage)
            .subscribeOn(Schedulers.io())
            //设置事件接受在UI线程以达到UI显示的目的
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                val string = it!!.body()!!
                adapter!!.setList(string.data!!.datalist)
            }
        adapter!!.setOnItemClickListener {adapter, view, position ->

        }
        return root
    }
    inner class HealPage(
        val start: String,
        val size: String,
        val arctype: String,
        val wmstart: String,
        val flag: String
    )

}