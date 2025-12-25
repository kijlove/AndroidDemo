package com.kijlee.android.demo.ui.view

import android.app.Activity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ActivityMyViewBinding
import kotlin.properties.Delegates

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.view
 * @ClassName:      MyViewActivity
 * @Author:     kij
 * @Description:  自定义View界面
 * @Date:    2025/1/12 14:48
 * @Version:    1.0
 */
class MyViewActivity: AppCompatActivity() {

     var _binding:ActivityMyViewBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMyViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startBtn.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(baseContext, R.anim.scaleanim)
            animation.duration = 500
            animation.fillAfter = true
            binding.tv.startAnimation(animation)
        }


    }
}