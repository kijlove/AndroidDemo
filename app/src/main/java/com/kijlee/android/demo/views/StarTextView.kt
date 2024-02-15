package com.kijlee.android.demo.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import com.beardedhen.androidbootstrap.AwesomeTextView
import com.beardedhen.androidbootstrap.BootstrapEditText
import com.kijlee.android.demo.R

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.views
 * @ClassName:      EndStartTextView
 * @Author:     kij
 * @Description:  尾部带*的TextVIew
 * @Date:    2022/2/9 5:31 下午
 * @Version:    1.0
 */
class StarTextView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    AwesomeTextView(context, attrs, defStyleAttr) {


    constructor(context: Context?, attrs: AttributeSet?) : this(context!!, attrs!!, 0)

    constructor(context: Context?) : this(context!!, null!!, 0)

    var myTextPaint: TextPaint? = null
    var startText: String? = null
    var endText: String? = null
    var startColor: Int? = null
    var endColor: Int? = null
    var location: Int? = null

    init {
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.StarTextView)
        startText = a.getString(R.styleable.StarTextView_startText)
        startColor = a.getColor(R.styleable.StarTextView_startColor, 0)
        endText = a.getString(R.styleable.StarTextView_endText)
        endColor = a.getColor(R.styleable.StarTextView_endColor, 0)
        location = a.getInt(R.styleable.StarTextView_location, 0)

        myTextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        myTextPaint!!.density = getResources().getDisplayMetrics().density
        if (startColor == null || startColor == 0) {
            startColor = currentTextColor
        }
        if (endColor == null || endColor == 0) {
            endColor = currentTextColor
        }
        myTextPaint!!.setColor(startColor!!);
        myTextPaint!!.setTextSize(textSize);

        when(location){
            0->{endText=""}
            1->{startText=""}
            else->{
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onDraw(canvas: Canvas) {

        if (startText == null) {
            startText = ""
        } else {
            //文字结尾添加其他文字
            canvas!!.drawText(
                startText!!,
                paddingLeft.toFloat(),
                (getPaddingTop() - getPaint().getFontMetricsInt().top).toFloat(),
                myTextPaint!!
            )
        }
        if (endText == null) {
            endText = ""
        } else {
            myTextPaint!!.setColor(endColor!!);
            val rightWidth = getPaint().measureText(startText.toString()) +
                    getPaint().measureText(text.toString()) +
                    paddingLeft.toFloat() + paddingRight.toFloat()
            canvas!!.drawText(
                endText!!,
                rightWidth,
                (getPaddingTop() - getPaint().getFontMetricsInt().top).toFloat(),
                myTextPaint!!
            )
        }




        super.onDraw(canvas)
    }

    //用于设置setText中的文本距离右边距的长度
    override fun getCompoundPaddingRight(): Int {
        return super.getCompoundPaddingRight() +
                getPaint().measureText(endText).toInt() +
                paddingRight
    }

    override fun getCompoundPaddingLeft(): Int {
        return super.getCompoundPaddingLeft() +
                getPaint().measureText(startText).toInt() +
                paddingLeft
    }
}