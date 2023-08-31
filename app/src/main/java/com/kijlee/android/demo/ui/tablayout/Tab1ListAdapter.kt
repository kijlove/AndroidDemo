package com.kijlee.android.demo.ui.tablayout

import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ReItemBinding
import com.kijlee.android.demo.databinding.ReItem1Binding
import com.kijlee.android.demo.databinding.ReItem2Binding
import com.kijlee.android.demo.databinding.ReItem3Binding
import com.kijlee.android.demo.databinding.ReItem4Binding
import com.kijlee.android.demo.databinding.ReItem5Binding
import com.kijlee.android.demo.entity.ClickEntity

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.tablayout
 * @ClassName:      Tab1ListAdapter
 * @Author:     kij
 * @Description:  tab1的列表适配器
 * @Date:    2022/1/22 8:30 下午
 * @Version:    1.0
 */
class Tab1ListAdapter constructor( data:MutableList<ClickEntity>) :
    BaseMultiItemQuickAdapter<ClickEntity, BaseDataBindingHolder<ViewDataBinding>>(data),
    OnItemClickListener, OnItemChildClickListener{
    init{

        addItemType(ClickEntity.TYPE_ITEM_0, R.layout.re_item)
        addItemType(ClickEntity.TYPE_ITEM_1, R.layout.re_item1)
        addItemType(ClickEntity.TYPE_ITEM_2, R.layout.re_item2)
        addItemType(ClickEntity.TYPE_ITEM_3, R.layout.re_item3)
        addItemType(ClickEntity.TYPE_ITEM_4, R.layout.re_item4)
        addItemType(ClickEntity.TYPE_ITEM_5, R.layout.re_item5)

        addChildClickViewIds(
            R.id.text1,
            R.id.text2,
            R.id.text3,
            R.id.text4,
            R.id.text5
        )

        addChildLongClickViewIds(
            R.id.text1,
            R.id.text2,
            R.id.text3,
            R.id.text4,
            R.id.text5
        )
    }

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: ClickEntity) {

        when(holder.itemViewType){
            ClickEntity.TYPE_ITEM_1->{
                // 获取 Binding
                val binding = (holder.dataBinding as ReItem1Binding?)!!
                binding.name1 = "ReItem1Bindingitem"
                binding.executePendingBindings()
            }
            ClickEntity.TYPE_ITEM_2->{
                // 获取 Binding
                val binding =(holder.dataBinding as ReItem2Binding?)!!
                binding.title = "ReItem2--title"
                binding.subTitle = "ReItem2--subTitle"
                binding.executePendingBindings()
            }
            ClickEntity.TYPE_ITEM_3->{
                // 获取 Binding
                val binding  = (holder.dataBinding as ReItem3Binding?)!!
                binding.name3 = "ReItem3Bindingitem"
                binding.executePendingBindings()
            }
            ClickEntity.TYPE_ITEM_4->{
                // 获取 Binding
                val binding  = (holder.dataBinding as ReItem4Binding?)!!
                binding.name4 = "ReItem4Bindingitem"
                binding.executePendingBindings()
            }
            ClickEntity.TYPE_ITEM_5->{
                // 获取 Binding
                val binding = (holder.dataBinding as ReItem5Binding?)!!
                binding.name5 = "ReItem5Bindingitem"
                binding.executePendingBindings()
            }
            else->{
                val binding = (holder.dataBinding as ReItemBinding?)!!
                binding.name0 = "ReItem0Bindingitem"
                binding.executePendingBindings()

            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Toast.makeText(view.context, "childView click$position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Toast.makeText(view.context, "嵌套RecycleView item 收到: 点击了第 $position 一次", Toast.LENGTH_SHORT)
            .show()
    }
}

