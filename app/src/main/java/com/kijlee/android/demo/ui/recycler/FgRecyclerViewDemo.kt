package com.kijlee.android.demo.ui.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum
import com.github.houbb.pinyin.util.PinyinHelper
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgRecyclerViewDemoBinding
import com.kijlee.android.demo.entity.sql.litepal.ChinaTownLitePal
import com.kijlee.android.demo.ui.main.FgMain
import com.orhanobut.logger.Logger
import org.litepal.LitePal


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.recycler
 * @ClassName:      FgRecyclerViewDemo
 * @Author:     kij
 * @Description:  recycler view功能测试
 * @Date:    2023/7/12 18:37
 * @Version:    1.0
 */
class FgRecyclerViewDemo : Fragment() {
    var _layoutBind: FgRecyclerViewDemoBinding? = null
    var item = ""
    var fileAdapter : ReItem2Adapter? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

    override fun onDestroy() {
        super.onDestroy()
        _layoutBind = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgMain.Item_Id)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgMain.Item_Id).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgRecyclerViewDemoBinding.inflate(layoutInflater)

        val root: View = binding.root
        val fileListStr:MutableList<String> = ArrayList<String>()

        val city = LitePal.where("town_id == ? AND county_id == ?","0","0")
            .find(ChinaTownLitePal::class.java)
        for(item in city){
            fileListStr.add(item.name.toString())
            Logger.e("拼音========${PinyinHelper.toPinyin(item.name, PinyinStyleEnum.FIRST_LETTER)}")
        }

        fileAdapter = ReItem2Adapter(R.layout.re_item2,fileListStr)
        binding.recyclerView.adapter = fileAdapter
        binding.recyclerView.addItemDecoration( SimplePaddingDecoration(requireContext()));
        binding.recyclerView.addItemDecoration( LeftAndRightTagDecoration(requireContext()));
        binding.recyclerView.addItemDecoration(PinnedSectionDecoration(requireContext(), object : DecorationCallback {
            override fun getGroupId(position: Int): Long {
                return Character.toUpperCase(PinyinHelper.toPinyin(fileListStr[position]).toCharArray()[0]).toInt().toLong()
            }

            override fun getGroupFirstLine(position: Int): String {
                return fileListStr[position].substring(0, 1).uppercase()
            }
        }))

        return root
    }

    //fun that print a random number
    


    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}