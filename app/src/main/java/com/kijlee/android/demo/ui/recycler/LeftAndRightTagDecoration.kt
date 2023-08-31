package com.kijlee.android.demo.ui.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kijlee.android.demo.R


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.recycler
 * @ClassName:      LeftAndRightTagDecoration
 * @Author:     kij
 * @Description:  左右绘制
 * @Date:    2023/7/13 15:11
 * @Version:    1.0
 */
class LeftAndRightTagDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val tagWidth: Int
    private val leftPaint: Paint
    private val rightPaint: Paint

    init {
        leftPaint = Paint()
        leftPaint.setColor(ContextCompat.getColor(context, R.color.lightorange))
        rightPaint = Paint()
        rightPaint.setColor(ContextCompat.getColor(context, R.color.custom_default_fill))
        tagWidth = context.resources.getDimensionPixelSize(R.dimen.default_padding)
    }

   override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val pos = parent.getChildAdapterPosition(child)
            val isLeft = pos % 2 == 0
            if (isLeft) {
                val left = child.left.toFloat()
                val right = left + tagWidth
                val top = child.top.toFloat()
                val bottom = child.bottom.toFloat()
                c.drawRect(left, top, right, bottom, leftPaint)
            } else {
                val right = child.right.toFloat()
                val left = right - tagWidth
                val top = child.top.toFloat()
                val bottom = child.bottom.toFloat()
                c.drawRect(left, top, right, bottom, rightPaint)
            }
        }
    }
}