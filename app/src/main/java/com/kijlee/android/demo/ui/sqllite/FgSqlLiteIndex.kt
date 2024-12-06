package com.kijlee.android.demo.ui.sqllite

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.beardedhen.androidbootstrap.BootstrapButton
import com.kijlee.android.demo.App
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgSqlLiteIndexBinding
import com.kijlee.android.demo.entity.sql.room.ChinaCityRoom
import com.kijlee.android.demo.entity.sql.room.ChinaTownViewModel
import com.kijlee.android.demo.entity.sql.room.ChinaTownViewModelFactory
import com.kijlee.android.demo.ui.main.FgMain
import com.kijlee.android.demo.ui.main.FgSqlLite
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.sqllite
 * @ClassName:      Fg_SqlLiteIndex
 * @Author:     kij
 * @Description:  数据库整理主页
 * @Date:    2022/1/26 1:31 下午
 * @Version:    1.0
 */
class FgSqlLiteIndex : Fragment() {


    var _layoutBind: FgSqlLiteIndexBinding? = null
    var item = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!
    private val viewModel: ChinaTownViewModel by activityViewModels {
        ChinaTownViewModelFactory(
            (activity?.application as App).database.chinaTownRoomDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgMain.Item_Id)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgMain.Item_Id).toString()
                Logger.e("item===$item")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgSqlLiteIndexBinding.inflate(layoutInflater)

        val root: View = binding.root

        val dataManage = DBManager(requireContext())
        val citys = dataManage.copyDataToLitepal()
        for (i in citys){
            viewModel.insertItem(i)
        }

        binding.setOnClickListener {

            val demoName = (it as BootstrapButton).text.toString()

            var bundle = Bundle()
            bundle.putString(FgSqlLite.SqlLite_Name, demoName)
            when(it.id){
                R.id.to_green_dao->{// GreenDao数据库使用
                    it.findNavController().navigate(R.id.to_green_dao, bundle)
                }
                R.id.to_object_box->{// ObjectBox数据库使用
                    it.findNavController().navigate(R.id.to_object_box, bundle)
                }
                R.id.to_litepal->{// LitePal数据库使用
                    it.findNavController().navigate(R.id.to_litepal, bundle)
                }
            }
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.map {
//                Logger.e(it.toString())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }



}