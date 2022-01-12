package com.kijlee.android.demo

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kijlee.android.demo.databinding.ReItemTextBinding
import com.kijlee.android.demo.entity.User

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo
 * @ClassName:      DataBindingAdapter
 * @Author:     kij
 * @Description:  测试列表适配器
 * @Date:    2022/1/4 9:05 下午
 * @Version:    1.0
 */
class DataBindingAdapter(layoutResId: Int, data: MutableList<String>?) :
    BaseQuickAdapter<String, BaseDataBindingHolder<ReItemTextBinding>>(layoutResId, data) {


    override fun convert(holder: BaseDataBindingHolder<ReItemTextBinding>, item: String) {
        // 获取 Binding
        val binding: ReItemTextBinding = holder.dataBinding!!
        if (binding != null) {
            binding.demo = item
            binding.executePendingBindings()
        }
    }
}
