package com.kijlee.android.demo.ui.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kijlee.android.demo.R


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.recycler
 * @ClassName:      SimplePaddingDecoration
 * @Author:     kij
 * @Description:  测试列表 item
 * @Date:    2023/7/13 13:41
 * @Version:    1.0
 */
class SimplePaddingDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val dividerHeight: Int
    private var dividerPaint: Paint? = null

    init {
        dividerPaint = Paint()
        dividerPaint!!.setColor(ContextCompat.getColor(context, R.color.purple_200))

        dividerHeight = context.resources.getDimensionPixelSize(R.dimen.divider_height)
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight //类似加了一个bottom padding
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until childCount - 1) {
            val view = parent.getChildAt(i)
            val top = view.bottom.toFloat()
            val bottom = (view.bottom + dividerHeight).toFloat()
            c.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint!!)
        }
    }
}