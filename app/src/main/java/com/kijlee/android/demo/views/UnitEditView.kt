package com.kijlee.android.demo.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView.BufferType
import androidx.annotation.RequiresApi
import com.beardedhen.androidbootstrap.BootstrapEditText
import com.kijlee.android.demo.R

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.views
 * @ClassName:      MyEditView
 * @Author:     kij
 * @Description:  自定义输入框
 * @Date:    2022/2/8 5:02 下午
 * @Version:    1.0
 */
class UnitEditView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    BootstrapEditText(context, attrs, defStyleAttr) {


    constructor(context: Context?, attrs: AttributeSet?) : this(context!!, attrs!!, android.R.attr.editTextStyle)

    constructor(context: Context?) : this(context!!, null!!, android.R.attr.editTextStyle)

    var myTextPaint: TextPaint? = null
    var myUnit: String? = null
    var myUnitColor: Int? = null

    init {
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.UnitEditView)
        myUnit = a.getString(R.styleable.UnitEditView_unitText)
        myUnitColor = a.getColor(R.styleable.UnitEditView_unitTextColor,0)

        myTextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        myTextPaint!!.density = getResources().getDisplayMetrics().density
        if(myUnitColor==null|| myUnitColor==0){
            myUnitColor = currentTextColor
        }
        myTextPaint!!.setColor(myUnitColor!!);
        myTextPaint!!.setTextSize(textSize);

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onDraw(canvas: Canvas) {
        if (myUnit == null) {
            myUnit = ""
        }
        //文字结尾添加其他文字
//        canvas!!.drawText(
//            myUnit!!,
//            getPaint().measureText(text.toString()) + paddingLeft.toFloat() + paddingRight.toFloat(),
//            (getPaddingTop() - getPaint().getFontMetricsInt().top).toFloat(),
//            myTextPaint!!
//        )
        canvas!!.drawText(
            myUnit!!,
            width.toFloat()-getPaint().measureText(myUnit)-paddingRight,
            (getPaddingTop() - getPaint().getFontMetricsInt().top).toFloat(),
            myTextPaint!!
        )

        super.onDraw(canvas)
    }

    //用于设置setText中的文本距离右边距的长度
    override fun getCompoundPaddingRight(): Int {
        return super.getCompoundPaddingRight()+getPaint().measureText(myUnit).toInt()+paddingRight
    }
}