package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgRetrofitBinding
import com.kijlee.android.demo.net.NetWorkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.main
 * @ClassName:      FgRetrofit
 * @Author:     kij
 * @Description:  retrofit网络请求
 * @Date:    2022/1/8 9:38 下午
 * @Version:    1.0
 */
class FgRetrofit : Fragment() {


    var _layoutBind: FgRetrofitBinding? = null
    var item = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

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

        _layoutBind = FgRetrofitBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.setOnClickListener {
            when (it.id) {
                R.id.get_luffy_city -> {
                    NetWorkManager.instance.mLuffyCityService.actual(2, 0, 5)
                        .subscribeOn(Schedulers.io())
                        //设置事件接受在UI线程以达到UI显示的目的
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { it ->
                            val string = it!!.body()!!.string()
                            binding.result = string
                        }
                }
            }
        }
        return root
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }

}