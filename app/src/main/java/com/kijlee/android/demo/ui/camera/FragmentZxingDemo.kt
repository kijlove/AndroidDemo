package com.kijlee.android.demo.ui.camera

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.DecodeHintType
import com.google.zxing.Result
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FragmentZxingDemoBinding
import com.kijlee.android.demo.ui.camera.camera.CameraManager
import com.kijlee.android.demo.ui.main.FgMain
import com.orhanobut.logger.Logger
import java.io.IOException

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.camera
 * @ClassName:      FragmentZxingDemo
 * @Author:     kij
 * @Description:  zxing 二维码测试
 * @Date:    2023/9/11 21:56
 * @Version:    1.0
 */
class FragmentZxingDemo  : Fragment(), SurfaceHolder.Callback {
    var _layoutBind: FragmentZxingDemoBinding? = null //
    var item = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

    var hasSurface = false
     var handler: CaptureActivityHandler? = null

    var cameraManager: CameraManager? = null
     var decodeHints: Map<DecodeHintType, *>? = null
    var decodeFormats: Collection<BarcodeFormat>? = null
     val characterSet: String? = null
     var savedResultToShow: Result? = null

    override fun onDestroy() {
        super.onDestroy()
        _layoutBind = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // CameraManager must be initialized here, not in onCreate(). This is necessary because we don't
        // want to open the camera driver and measure the screen size if we're going to show the help on
        // first launch. That led to bugs where the scanning rectangle was the wrong size and partially
        // off screen.

        arguments?.let {
            if (it.containsKey(FgMain.Item_Id)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgMain.Item_Id).toString()

                Toast.makeText(requireContext(), "$item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        cameraManager = CameraManager(requireContext())
        _layoutBind = FragmentZxingDemoBinding.inflate(layoutInflater)
        val root: View = binding.root
        binding.viewfinderView.setCameraManager(cameraManager)
        initView()
        return root
    }

    fun initView(){


        val surfaceHolder = binding.previewView.holder
        if (hasSurface) {
            // The activity was paused but not stopped, so the surface still exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(surfaceHolder)
        } else {
            // Install the callback and wait for surfaceCreated() to init the camera.
            surfaceHolder.addCallback(this)
        }
    }

    //fun that print a random number



    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }


    fun initCamera(surfaceHolder: SurfaceHolder?) {
        checkNotNull(surfaceHolder) { "No SurfaceHolder provided" }
        if (cameraManager!!.isOpen) {
            Logger.e("initCamera() while already open -- late SurfaceView callback?")
            return
        }
        try {
            cameraManager!!.openDriver(surfaceHolder)
            // 创建处理程序将启动预览，这也可能引发运行时异常。
            if (handler == null) {

                val intent: Intent = requireActivity().getIntent()

                decodeFormats = DecodeFormatManager.parseDecodeFormats(intent)
                decodeHints = DecodeHintManager.parseDecodeHints(intent)
//                handler = CaptureActivityHandler(this@FragmentZxingDemo,
//                    decodeFormats,
//                    decodeHints,
//                    characterSet,
//                    cameraManager
//                )
            }
            decodeOrStoreSavedBitmap(null,null)
        } catch (ioe: IOException) {
        } catch (e: RuntimeException) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
        }
    }


    override fun surfaceCreated(holder: SurfaceHolder) {
        if (holder == null) {
            Logger.e( "*** WARNING *** surfaceCreated() gave us a null surface!===================")
        }
        if (!hasSurface) {
            hasSurface = true
            initCamera(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        hasSurface = false
    }


    /**
     * 已找到有效的条形码，因此请指示成功并显示结果。
     *
     * @param rawResult The contents of the barcode.
     * @param scaleFactor amount by which thumbnail was scaled
     * @param barcode   A greyscale bitmap of the camera data which was decoded.
     */
    fun handleDecode(rawResult: Result, barcode: Bitmap?, scaleFactor: Float) {}

    fun drawViewfinder() {
       binding.viewfinderView.drawViewfinder()
    }

    fun decodeOrStoreSavedBitmap(bitmap: Bitmap?, result: Result?) {
        // Bitmap isn't used yet -- will be used soon
        if (handler == null) {
            savedResultToShow = result
        } else {
            if (result != null) {
                savedResultToShow = result
            }
            if (savedResultToShow != null) {
                val message = Message.obtain(handler, R.id.decode_succeeded, savedResultToShow)
                handler!!.sendMessage(message)
            }
            savedResultToShow = null
        }
    }
}