package com.kijlee.android.demo.ui.sqllite.litepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.FgLitepalBinding
import com.kijlee.android.demo.entity.sql.litepal.ChinaTownLitePal
import com.kijlee.android.demo.ui.main.FgSqlLite
import com.orhanobut.logger.Logger
import org.litepal.LitePal

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.sqllite.litepal
 * @ClassName:      FgLItePal
 * @Author:     kij
 * @Description:  litepal练习数据库
 * @Date:    2022/12/1 22:56
 * @Version:    1.0
 */
class FgLitePal : Fragment() {


    var _layoutBind: FgLitepalBinding? = null
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

        _layoutBind = FgLitepalBinding.inflate(layoutInflater)
        val root: View = binding.root
        binding.sqlName = item
        getAllCity()
        return root
    }
    fun getAllCity(){
        val city = LitePal.findAll(ChinaTownLitePal::class.java)
        for(item in 0..100){
            Logger.e("name--------"+city[item].name.toString())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }

}