package com.kijlee.android.demo.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.views
 * @ClassName:      BasisView
 * @Author:     kij
 * @Description:  自定义view第一章第一个view
 * @Date:    2024/2/22 22:57
 * @Version:    1.0
 */
class BasisView constructor(context: Context, attributes: AttributeSet?, defStyle: Int) :
    View(context, attributes, defStyle) {


    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)
    constructor(context: Context) : this(context, null, 0)

    init {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = Color.RED
//        paint.style = Paint.Style.STROKE
//        paint.style = Paint.Style.FILL
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 50f
        canvas.drawCircle(190f, 200f, 150f, paint)
        paint.color = Color.YELLOW
        canvas.drawCircle(190f, 200f, 100f, paint)


    }

}