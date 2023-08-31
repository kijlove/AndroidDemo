package com.kijlee.android.demo.ui.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextPaint
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kijlee.android.demo.R
import java.util.Locale


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.recycler
 * @ClassName:      SectionDecoration
 * @Author:     kij
 * @Description:   标题头分类数据
 * @Date:    2023/7/13 16:00
 * @Version:    1.0
 */
class SectionDecoration(context: Context, decorationCallback: DecorationCallback) :
    RecyclerView.ItemDecoration() {
    private val callback: DecorationCallback
    private val textPaint: TextPaint
    private val paint: Paint
    private val topGap: Int
    private val fontMetrics: Paint.FontMetrics

    init {

        callback = decorationCallback
        paint = Paint()
        paint.setColor(ContextCompat.getColor(context, R.color.purple_200))
        textPaint = TextPaint()
        textPaint.typeface = Typeface.DEFAULT_BOLD
        textPaint.isAntiAlias = true
        textPaint.textSize = 80f
        textPaint.color = Color.BLACK
        fontMetrics = Paint.FontMetrics()
        textPaint.getFontMetrics(fontMetrics)
        textPaint.textAlign = Paint.Align.LEFT
        topGap = context.resources.getDimensionPixelSize(R.dimen.dp_40) //32dp
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view!!, parent, state!!)
        val pos = parent.getChildAdapterPosition(view)
        Log.i(TAG, "getItemOffsets：$pos")
        val groupId = callback.getGroupId(pos)
        if (groupId < 0) return
        if (pos == 0 || isFirstInGroup(pos)) { //同组的第一个才添加padding
            outRect.top = topGap
        } else {
            outRect.top = 0
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft.toFloat()
        val right = parent.width - parent.paddingRight.toFloat()
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val groupId = callback.getGroupId(position)
            if (groupId < 0) return
            val textLine = callback.getGroupFirstLine(position).uppercase(Locale.getDefault())
            if (position == 0 || isFirstInGroup(position)) {
                val top = (view.top - topGap).toFloat()
                val bottom = view.top.toFloat()
                c.drawRect(left, top, right, bottom, paint) //绘制红色矩形
                c.drawText(textLine, left, bottom, textPaint) //绘制文本
            }
        }
    }

    private fun isFirstInGroup(pos: Int): Boolean {
        return if (pos == 0) {
            true
        } else {
            val prevGroupId = callback.getGroupId(pos - 1)
            val groupId = callback.getGroupId(pos)
            prevGroupId != groupId
        }
    }


    companion object {
        private const val TAG = "SectionDecoration"
    }
}