package com.kijlee.android.demo.ui.tablayout

import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.*
import com.kijlee.android.demo.entity.ClickEntity
import com.kijlee.android.demo.entity.health.NewsBean
import com.kijlee.android.demo.ui.qmui.FgQmuiWebViewArgs

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.tablayout
 * @ClassName:      HealthAdapter
 * @Author:     kij
 * @Description:  健康界列表
 * @Date:    2022/3/13 10:34 上午
 * @Version:    1.0
 */
class HealthAdapter constructor( data:MutableList<NewsBean>) :
    BaseMultiItemQuickAdapter<NewsBean, BaseDataBindingHolder<ViewDataBinding>>(data),
    OnItemClickListener, OnItemChildClickListener {
    init{

        addItemType(NewsBean.TYPE_ITEM_0, R.layout.re_item_health_news)
        addItemType(NewsBean.TYPE_ITEM_1, R.layout.re_item_health_news)
        addItemType(NewsBean.TYPE_ITEM_2, R.layout.re_item2)
        addItemType(NewsBean.TYPE_ITEM_3, R.layout.re_item3)
        addItemType(NewsBean.TYPE_ITEM_4, R.layout.re_item4)
        addItemType(NewsBean.TYPE_ITEM_5, R.layout.re_item5)

        addChildClickViewIds(
            R.id.layout_position,
            R.id.image,
            R.id.title
        )

        addChildLongClickViewIds(
            R.id.layout_position,
            R.id.image,
            R.id.title
        )
    }

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: NewsBean) {

        when(holder.itemViewType){
            ClickEntity.TYPE_ITEM_0->{
                // 获取 Binding
                val binding = (holder.dataBinding as ReItemHealthNewsBinding?)!!
                binding.news = item

                val interval = 1000 * 1000.toLong()
                Glide.with(holder.itemView.context)
                    .asBitmap()
                    .load("https://files.cn-healthcare.com${item.litpic}") // Resize image view by change override size.
                    .apply(RequestOptions().frame(interval).override(339, 237))
                    .into(binding.image)
                binding.executePendingBindings()

                setOnItemClickListener(this)
                setOnItemChildClickListener(this)
            }
            ClickEntity.TYPE_ITEM_1->{
                // 获取 Binding
                val binding =(holder.dataBinding as ReItemHealthNewsBinding?)!!
                binding.news = item

                val interval = 1000 * 1000.toLong()
                Glide.with(holder.itemView.context)
                    .asBitmap()
                    .load("https://files.cn-healthcare.com${item.litpic}") // Resize image view by change override size.
                    .apply(RequestOptions().frame(interval).override(339, 237))
                    .into(binding.image)
                binding.executePendingBindings()

                setOnItemClickListener(this)
                setOnItemChildClickListener(this)
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
        val item = adapter.getItem(position) as NewsBean
        view.findNavController().navigate(R.id.to_fg_qmui_web_view,FgQmuiWebViewArgs.Builder().setUrl("${item.url}").build().toBundle())
    }
}