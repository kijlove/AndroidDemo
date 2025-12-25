package com.kijlee.android.demo.ui.qmui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgQmuiIndexBinding
import com.orhanobut.logger.Logger
import com.qmuiteam.qmui.skin.QMUISkinHelper
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.util.QMUIViewOffsetHelper
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2




/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.qmui
 * @ClassName:      FgQmuiIndex
 * @Author:     kij
 * @Description:  qmui 界面
 * @Date:    2022/10/28 15:13
 * @Version:    1.0
 */
class FgQmuiIndex : Fragment() {


    var _layoutBind: FgQmuiIndexBinding? = null
    var item = ""


    private val binding get() = _layoutBind!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgQmuiIndexBinding.inflate(layoutInflater)
//        _layoutBind =  DataBindingUtil.inflate(inflater, R.layout.fg_qmui_index, container, false)

        val root: View = binding.root

        val rectangle = Rect()
        requireActivity().window.getDecorView().getWindowVisibleDisplayFrame(rectangle)
        binding.qmuiIndexConstraint.addView(CycleFloatView(requireContext(),rectangle))


        return root
    }

    class CycleFloatView(context: Context?,parentView:Rect) : FrameLayout(context!!) {


        private val globalBtn: QMUIRadiusImageView2
        private val globalBtnOffsetHelper: QMUIViewOffsetHelper
        private val btnSize: Int
        private val touchSlop: Int
        private var touchDownX = 0f
        private var touchDownY = 0f
        private var lastTouchX = 0f
        private var lastTouchY = 0f
        private var isDragging = false
        private var isTouchDownInGlobalBtn = false

        init {

            btnSize = QMUIDisplayHelper.dp2px(context, 56)
            globalBtn = QMUIRadiusImageView2(context)
            globalBtn.setImageResource(R.mipmap.ic_launcher)
            globalBtn.scaleType = ImageView.ScaleType.CENTER_INSIDE
            globalBtn.setRadiusAndShadow(
                btnSize / 2,
                QMUIDisplayHelper.dp2px(getContext(), 16), 0.4f
            )
            globalBtn.borderWidth = 1
            globalBtn.borderColor =
                QMUIResHelper.getAttrColor(context, R.attr.qmui_skin_support_color_separator)
            globalBtn.setBackgroundColor(
                QMUIResHelper.getAttrColor(
                    context,
                    R.attr.app_skin_common_background
                )
            )
            globalBtn.setOnClickListener(object : OnClickListener {
               override fun onClick(v: View?) {
                   Logger.e("parentView.x--------${v!!.pivotX}")
                   Logger.e("parentView.y--------${v!!.pivotY}")

               }
            })
            val globalBtnLp = FrameLayout.LayoutParams(btnSize, btnSize)
            Logger.e("parentView.width--------${btnSize}")
            globalBtnLp.gravity = Gravity.BOTTOM or Gravity.RIGHT
            globalBtnLp.bottomMargin = parentView.bottom-340
            globalBtnLp.rightMargin = parentView.right-btnSize
            val builder = QMUISkinValueBuilder.acquire()
            builder.background(R.attr.app_skin_common_background)
            builder.border(R.attr.qmui_skin_support_color_separator)
            builder.tintColor(R.attr.app_skin_common_img_tint_color)
            QMUISkinHelper.setSkinValue(globalBtn, builder)
            builder.release()
            addView(globalBtn, globalBtnLp)
            globalBtnOffsetHelper = QMUIViewOffsetHelper(globalBtn)
            touchSlop = ViewConfiguration.get(getContext()).scaledTouchSlop
        }

        override  fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
            super.onLayout(changed, left, top, right, bottom)
            globalBtnOffsetHelper.onViewLayout()
        }

        override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
            val x = event.x
            val y = event.y
            val action = event.action
            if (action == MotionEvent.ACTION_DOWN) {
                isTouchDownInGlobalBtn = isDownInGlobalBtn(x, y)
                lastTouchX = x
                touchDownX = lastTouchX
                lastTouchY = y
                touchDownY = lastTouchY
            } else if (action == MotionEvent.ACTION_MOVE) {
                if (!isDragging && isTouchDownInGlobalBtn) {
                    val dx = (x - touchDownX).toInt()
                    val dy = (y - touchDownY).toInt()
                    if (Math.sqrt((dx * dx + dy * dy).toDouble()) > touchSlop) {
                        isDragging = true
                    }
                }
                if (isDragging) {
                    var dx = (x - lastTouchX).toInt()
                    var dy = (y - lastTouchY).toInt()
                    val gx = globalBtn.left
                    val gy = globalBtn.top
                    val gw = globalBtn.width
                    val w: Int = getWidth()
                    val gh = globalBtn.height
                    val h: Int = getHeight()
                    if (gx + dx < 0) {
                        dx = -gx
                    } else if (gx + dx + gw > w) {
                        dx = w - gw - gx
                    }
                    if (gy + dy < 0) {
                        dy = -gy
                    } else if (gy + dy + gh > h) {
                        dy = h - gh - gy
                    }
                    globalBtnOffsetHelper.leftAndRightOffset =
                        globalBtnOffsetHelper.leftAndRightOffset + dx
                    globalBtnOffsetHelper.topAndBottomOffset =
                        globalBtnOffsetHelper.topAndBottomOffset + dy
                }
                lastTouchX = x
                lastTouchY = y
            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                isDragging = false
                isTouchDownInGlobalBtn = false
            }
            return isDragging
        }

        private fun isDownInGlobalBtn(x: Float, y: Float): Boolean {
            return globalBtn.left < x && globalBtn.right > x && globalBtn.top < y && globalBtn.bottom > y
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            val x = event.x
            val y = event.y
            val action = event.action
            if (action == MotionEvent.ACTION_DOWN) {
                isTouchDownInGlobalBtn = isDownInGlobalBtn(x, y)
                lastTouchX = x
                touchDownX = lastTouchX
                lastTouchY = y
                touchDownY = lastTouchY
            } else if (action == MotionEvent.ACTION_MOVE) {
                if (!isDragging && isTouchDownInGlobalBtn) {
                    val dx = (x - touchDownX).toInt()
                    val dy = (y - touchDownY).toInt()
                    if (Math.sqrt((dx * dx + dy * dy).toDouble()) > touchSlop) {
                        isDragging = true
                    }
                }
                if (isDragging) {
                    var dx = (x - lastTouchX).toInt()
                    var dy = (y - lastTouchY).toInt()
                    val gx = globalBtn.left
                    val gy = globalBtn.top
                    val gw = globalBtn.width
                    val w: Int = getWidth()
                    val gh = globalBtn.height
                    val h: Int = getHeight()
                    if (gx + dx < 0) {
                        dx = -gx
                    } else if (gx + dx + gw > w) {
                        dx = w - gw - gx
                    }
                    if (gy + dy < 0) {
                        dy = -gy
                    } else if (gy + dy + gh > h) {
                        dy = h - gh - gy
                    }
                    globalBtnOffsetHelper.leftAndRightOffset =
                        globalBtnOffsetHelper.leftAndRightOffset + dx
                    globalBtnOffsetHelper.topAndBottomOffset =
                        globalBtnOffsetHelper.topAndBottomOffset + dy
                }
                lastTouchX = x
                lastTouchY = y
            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                isDragging = false
                isTouchDownInGlobalBtn = false
            }
            return isDragging || super.onTouchEvent(event)
        }
    }
}