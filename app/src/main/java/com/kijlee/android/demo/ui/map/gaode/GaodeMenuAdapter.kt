package com.kijlee.android.demo.ui.map.gaode

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.*
import com.kijlee.android.demo.entity.ClickEntity
import com.kijlee.android.demo.entity.map.gaode.DemoDetails
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.map.gaode
 * @ClassName:      GaodeMenuAdapter
 * @Author:     kij
 * @Description:  高德地图菜单适配器
 * @Date:    2022/3/10 10:33 下午
 * @Version:    1.0
 */
class GaodeMenuAdapter(data: MutableList<DemoDetails>?) :
    BaseMultiItemQuickAdapter<DemoDetails, BaseDataBindingHolder<ViewDataBinding>>( data) ,
    OnItemClickListener, OnItemChildClickListener {
    init{

        addItemType(ClickEntity.TYPE_ITEM_0, R.layout.re_item_title)
        addItemType(ClickEntity.TYPE_ITEM_1, R.layout.re_item_map_menu,)

        addChildClickViewIds(
            R.id.title,
            R.id.map_menu,
        )

        addChildLongClickViewIds(
            R.id.title,
            R.id.map_menu,
        )
    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Toast.makeText(view.context, "childView click$position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Toast.makeText(view.context, "嵌套RecycleView item 收到: 点击了第 $position 一次", Toast.LENGTH_SHORT).show()
    }

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: DemoDetails) {

        when(holder.itemViewType){
            ClickEntity.TYPE_ITEM_0->{
                // 获取 Binding
                val binding: ReItemTitleBinding = holder.dataBinding!! as ReItemTitleBinding
                binding.title = holder.itemView.context.getString(item.titleId)
                binding.executePendingBindings()
            }
            ClickEntity.TYPE_ITEM_1->{
                // 获取 Binding
                val binding: ReItemMapMenuBinding = holder.dataBinding!!  as ReItemMapMenuBinding
                binding.demoDetails = item
//        val view = holder.getView<QMUICommonListItemView>(R.id.text1)
                binding.mapMenu.apply {
                    detailText =holder.itemView.context.getString(item.descriptionId)
                    text =  holder.itemView.context.getString(item.titleId)
                }
                binding.executePendingBindings()
            }
        }
    }
}
