package com.kijlee.android.demo.ui.folder

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kijlee.android.demo.databinding.ReFileBinding
import com.kijlee.android.demo.databinding.ReItemTextBinding

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.folder
 * @ClassName:      FileListAdapter
 * @Author:     kij
 * @Description:  文件列表
 * @Date:    2023/3/22 23:19
 * @Version:    1.0
 */
class FileListAdapter (layoutResId: Int, data: MutableList<String>?) :
    BaseQuickAdapter<String, BaseDataBindingHolder<ReFileBinding>>(layoutResId, data) {


    override fun convert(holder: BaseDataBindingHolder<ReFileBinding>, item: String) {
        // 获取 Binding
        val binding: ReFileBinding = holder.dataBinding!!
        binding.fileName = item
        binding.executePendingBindings()
    }
}
