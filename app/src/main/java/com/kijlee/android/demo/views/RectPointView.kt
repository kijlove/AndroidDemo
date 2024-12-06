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
 * @ClassName:      RectPointView
 * @Author:     kij
 * @Description:  绘制矩形 检测是否触摸
 * @Date:    2024/2/23 23:57
 * @Version:    1.0
 */
class RectPointView constructor(context: Context, attributes: AttributeSet?, defStyle:Int) : View(context,attributes,defStyle) {
    var mX = 0
    var mY: Int = 0
    var mPaint: Paint? = null
    var mrect: Rect? = null
    constructor(context: Context, attributes: AttributeSet?) : this(context,attributes, 0)
    constructor(context: Context) : this(context,null, 0)
    init {

        mPaint = Paint()
        mPaint!!.setStyle(Paint.Style.STROKE)
        mrect = Rect(100, 10, 300, 100)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mrect!!.contains(mX,mY)){
            mPaint!!.setColor(Color.RED);
        }else {
            mPaint!!.setColor(Color.GREEN);
        }
        canvas.drawRect(mrect!!,mPaint!!);
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mX = event!!.getX().toInt();
        mY = event!!.getY().toInt();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            invalidate();
            return true;
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            mX = -1;
            mY = -1;
        }
        postInvalidate();

        return super.onTouchEvent(event)

    }
}