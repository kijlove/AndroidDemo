package com.kijlee.android.demo.ui.camera

import android.hardware.Camera
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.SurfaceLayoutBinding
import com.kijlee.android.demo.ui.base.BaseFragment

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.camera
 * @ClassName:      ShowSurfaceFragment
 * @Author:     kij
 * @Description:  surface 预览
 * @Date:    2024/11/2 15:01
 * @Version:    1.0
 */
class ShowSurfaceFragment:BaseFragment<SurfaceLayoutBinding>(),SurfaceHolder.Callback { //

    var surfaceHolder:SurfaceHolder? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SurfaceLayoutBinding.inflate(inflater, container,false)
         super.onCreateView(inflater, container, savedInstanceState)
        surfaceHolder = binding.cameraView.holder
        surfaceHolder!!.addCallback(this)
        return binding.root
    }
var camera: Camera? = null
    override fun surfaceCreated(p0: SurfaceHolder) {
        camera = Camera.open()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }


}