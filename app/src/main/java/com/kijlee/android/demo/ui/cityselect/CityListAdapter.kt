package com.kijlee.android.demo.ui.cityselect

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ReItemProvinceBinding
import com.kijlee.android.demo.entity.ChinaTown
import com.kijlee.android.demo.utils.Smg


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.cityselect
 * @ClassName:      CityListAdapter
 * @Author:     kij
 * @Description:  城市列表
 * @Date:    2022/10/19 22:32
 * @Version:    1.0
 */
class CityListAdapter : BaseQuickAdapter<ChinaTown?, BaseDataBindingHolder<ReItemProvinceBinding>>(R.layout.re_item_province) {

    override fun convert(holder: BaseDataBindingHolder<ReItemProvinceBinding>, item: ChinaTown?) {
        // 获取 Binding
        val binding: ReItemProvinceBinding? = holder!!.dataBinding
        if (binding != null) {
            binding.province = item!!
            binding.provinceName.setOnClickListener {
                Smg.showQMUITipDialog(holder!!.itemView.context,item.name!!,null,null)
            }

        }
    }
}

