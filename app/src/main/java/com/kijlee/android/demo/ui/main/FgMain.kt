package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.LayoutRecyclerViewBinding
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgMain
 * @Author:     kij
 * @Description:  主页数据
 * @Date:    2022/1/7 9:40 下午
 * @Version:    1.0
 */
class FgMain : Fragment() {


    var _layoutBind: LayoutRecyclerViewBinding? = null

    var userList: MutableList<String> = ArrayList()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = LayoutRecyclerViewBinding.inflate(layoutInflater)
        userList = resources.getStringArray(R.array.demo_array).toMutableList()

        val root: View = binding.root

        val recyclerView = binding.myRecyclerview
        val adapter = DataBindingAdapter(R.layout.re_item_text, userList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val demoName = adapter.getItem(position).toString()
            var bundle = Bundle()

            bundle.putString(
                Item_Id,
                demoName
            )

            Toast.makeText(requireContext(), "$demoName$position", Toast.LENGTH_SHORT).show()
            when (demoName) {
                "BootStrapper" -> {
                    Logger.e("BootStrapper");
                    view.findNavController().navigate(R.id.show_boot_strap, bundle)
                }
                "Retrofit" -> {
                    Logger.e("Retrofit");
                    view.findNavController().navigate(R.id.show_retrofit, bundle)
                }
                "TabLayout" -> {
                    Logger.e("tablayout");
                    view.findNavController().navigate(R.id.show_tablayout, bundle)
                }
                "SqlLite" -> {
                    Logger.e("SqlLite");
                    view.findNavController().navigate(R.id.show_sql_lite,
                        FgSqlLiteArgs.Builder().setDemoName(demoName).build().toBundle())
                }
                "高德地图" -> {
                    Logger.e("SqlLite");
                    view.findNavController().navigate(R.id.to_gaode,bundle)
                }
                "测试一个新闻列表" -> {
                    view.findNavController().navigate(R.id.to_fg_a_news,bundle)
                }
                "城市列表选择" -> {
                    view.findNavController().navigate(R.id.to_fg_city_select,bundle)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val Item_Id = "item_id"
    }

}