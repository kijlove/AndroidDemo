package com.kijlee.android.demo.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.geometry.Rect
import com.orhanobut.logger.Logger
import kotlin.concurrent.fixedRateTimer

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.view
 * @ClassName:      BaseView
 * @Author:     kij
 * @Description:  第一章View
 * @Date:    2025/1/12 15:38
 * @Version:    1.0
 */
class BaseView constructor(context: Context,atts:AttributeSet?,style:Int?): View(context,atts,style?:0) {
    constructor(context: Context,atts:AttributeSet):this(context,atts,0)
    constructor(context: Context):this(context,null,0)
    init {
        Logger.i("BaseView init")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = Color.GREEN  // 设置颜色
//        FILL, 中心
//        STROKE, 边缘
//        FILL_AND_STROKE; 中心加边缘
//        paint.style = Paint.Style.FILL  // 设置填充样式
        paint.style = Paint.Style.STROKE  // 设置填充样式
//        paint.style = Paint.Style.FILL_AND_STROKE  // 设置填充样式
        paint.strokeWidth = 5f // 设置画笔描边宽度
//        canvas.drawCircle(190f,200f,150f,paint) // 绘制原型
//        canvas.drawRGB(2525,0,255) // 绘制背景
//        绘制线
//        canvas.drawLine(100f,100f,200f,200f,paint)
//        绘制矩形
//        canvas.drawRect(10f, 10f, 100f, 100f, paint)
//        paint.style = Paint.Style.FILL
//        val rect = RectF(210f,10f,300f,100f)
//        canvas.drawRect(rect,paint)

//        绘制路径
//        val path = Path()
//        path.moveTo(10f,10f)
//        path.lineTo(10f,100f)
//        path.lineTo(300f,100f)
//        path.close()
//        canvas.drawPath(path,paint)

//        绘制弧线路径
//        val path = Path()
//        path.moveTo(10f,10f)
//        val rectF = RectF(100f,10f,200f,100f)
//        path.arcTo(rectF,60f,180f) //开始度数 和 结束度数
//        canvas.drawRect(rectF,paint)
//        path.arcTo(rectF,0f,90f,true)
//        canvas.drawPath(path,paint)

//        绘制区域Region
//        val region = Region(Rect(50,50,200,100))
//        drawRegion(canvas,region,paint)
//
//        val path = Path()
//        val rectF = RectF(50f,50f,200f,500f)
//        path.addOval(rectF,Path.Direction.CCW)
//        val region = Region()
//        region.setPath(path,Region(50,50,200,200))
//        drawRegion(canvas,region,paint)

//        区域相交
//        val region = Region(10,10,200,100)
//        region.union(Rect(10,10,50,300))
//        drawRegion(canvas,region,paint)

//        val rect1 = Rect(100,100,400,200)
//        val rect2 = Rect(200,0,300,300)
//        paint.style = Paint.Style.STROKE  // 设置填充样式
//        paint.strokeWidth = 2f
//        canvas.drawRect(rect1,paint)
//        canvas.drawRect(rect2,paint)


//        val region1 = Region(rect1)
//        val region2 = Region(rect2)
//        val region = Region()

//        region1.op(region2,Region.Op.INTERSECT) // 交集
//        region1.op(region2,Region.Op.DIFFERENCE) // 补集
//        region1.op(region2,Region.Op.REPLACE)  // 替换
//        region1.op(region2,Region.Op.REVERSE_DIFFERENCE) // 反转补集
//        region1.op(region2,Region.Op.UNION)  // 并集
//        region1.op(region2,Region.Op.XOR)  // 异并集
//        val paint_fill= Paint()
//        paint_fill.color = Color.GREEN
//        paint_fill.style = Paint.Style.FILL
//        region.op(region1,region2,Region.Op.INTERSECT)
//
//        drawRegion(canvas,region,paint_fill)

//        平移
//        canvas.translate(100f,100f)
//        val rect = Rect(0,0,400,220)
//        canvas.drawRect(rect,paint)


//        屏幕和canvas关系
//        val paint_red = generatePaint(Color.RED,Paint.Style.STROKE,3)
//        val rect1 = Rect(0,0,400,220)
//        canvas.drawRect(rect1,paint)
//        canvas.translate(100f,100f)
//        canvas.drawRect(rect1,paint_red)

        canvas.drawColor(Color.RED)
        canvas.save()
        canvas.clipRect(Rect(100,100,800,800))
        canvas.drawColor(Color.GREEN)
        canvas.restore()
        canvas.drawColor(Color.BLUE)




    }

    fun generatePaint(color:Int,style:Paint.Style,width:Int):Paint{
        val paint = Paint()
        paint.color = color
        paint.style = style
        paint.strokeWidth = width.toFloat()
        return paint
    }

    fun drawRegion(canvas: Canvas,rgn:Region,paint:Paint) {
        val regionIterator = RegionIterator(rgn)
        val rect = Rect()
        while (regionIterator.next(rect)){
            canvas.drawRect(rect,paint)
        }
    }

}