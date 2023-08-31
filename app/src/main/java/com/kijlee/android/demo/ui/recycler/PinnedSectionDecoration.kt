package com.kijlee.android.demo.ui.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextPaint
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kijlee.android.demo.R
import java.util.Locale


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.recycler
 * @ClassName:      PinnedSectionDecoration
 * @Author:     kij
 * @Description:  待标题头的list
 * @Date:    2023/7/14 10:24
 * @Version:    1.0
 */
class PinnedSectionDecoration(context: Context, decorationCallback: DecorationCallback) :
    RecyclerView.ItemDecoration() {
    private val callback: DecorationCallback
    private val textPaint: TextPaint
    private val paint: Paint
    private val topGap: Int
    private val fontMetrics: Paint.FontMetrics

    init {
        val res = context.resources
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
        topGap = res.getDimensionPixelSize(R.dimen.dp_40)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view!!, parent, state!!)
        val pos = parent.getChildAdapterPosition(view)
        val groupId = callback.getGroupId(pos)
        if (groupId < 0) return
        if (pos == 0 || isFirstInGroup(pos)) {
            outRect.top = topGap
        } else {
            outRect.top = 0
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.paddingLeft.toFloat()
        val right = parent.width - parent.paddingRight.toFloat()
        val lineHeight: Float = textPaint.textSize + fontMetrics.descent
        var preGroupId: Long
        var groupId: Long = -1
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            preGroupId = groupId
            groupId = callback.getGroupId(position)
            if (groupId < 0 || groupId == preGroupId) continue
            val textLine = callback.getGroupFirstLine(position).uppercase(Locale.getDefault())
            if (TextUtils.isEmpty(textLine)) continue
            val viewBottom = view.bottom
            var textY = Math.max(topGap, view.top).toFloat()
            if (position + 1 < itemCount) { //下一个和当前不一样移动当前
                val nextGroupId = callback.getGroupId(position + 1)
                if (nextGroupId != groupId && viewBottom < textY) { //组内最后一个view进入了header
                    textY = viewBottom.toFloat()
                }
            }
            c.drawRect(left, textY - topGap, right, textY, paint)
            c.drawText(textLine, left, textY, textPaint)
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
        private const val TAG = "PinnedSectionDecoration"
    }
}