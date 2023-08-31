package com.kijlee.android.demo.ui.recycler

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kijlee.android.demo.databinding.ReFileBinding
import com.kijlee.android.demo.databinding.ReItem2Binding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.recycler
 * @ClassName:      ReItem2Adapter
 * @Author:     kij
 * @Description:  列表适配器2 样式
 * @Date:    2023/7/13 14:35
 * @Version:    1.0
 */
class ReItem2Adapter (layoutResId: Int, data: MutableList<String>?) :
    BaseQuickAdapter<String, BaseDataBindingHolder<ReItem2Binding>>(layoutResId, data) {


    override fun convert(holder: BaseDataBindingHolder<ReItem2Binding>, item: String) {
        // 获取 Binding
        val binding: ReItem2Binding = holder.dataBinding!!
        binding.title = item
        binding.subTitle = "${item}${holder.adapterPosition}"
        binding.executePendingBindings()
    }
}
