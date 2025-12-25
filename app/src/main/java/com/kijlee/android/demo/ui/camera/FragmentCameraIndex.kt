package com.kijlee.android.demo.ui.camera

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.getIntent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.beardedhen.androidbootstrap.BootstrapButton
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.hjq.permissions.permission.PermissionLists
import com.hjq.permissions.permission.base.IPermission
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FragmentCameraIndexBinding
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.orhanobut.logger.Logger
import java.io.File

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.camera
 * @ClassName:      FragmentCameraIndex
 * @Author:     kij
 * @Description:  相机功能界面选择
 * @Date:    2023/10/5 09:59
 * @Version:    1.0
 */
class FragmentCameraIndex : Fragment() {

    private lateinit var startActivitylaunch: ActivityResultLauncher<Intent>
    var _layoutBind: FragmentCameraIndexBinding? = null
    var item = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _layoutBind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(ARG_ITEM_ID).toString()
            }
        }
        startActivitylaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
                if (it.resultCode==RESULT_OK) {

                    val currentDisplay = resources.displayMetrics
                    val dw = currentDisplay.widthPixels
                    val dh = currentDisplay.heightPixels

                    Logger.e("resultCode===${it.resultCode==RESULT_OK}")
                    val bitmapOptions = BitmapFactory.Options()
                    bitmapOptions.inJustDecodeBounds = true
                    var bitmap = BitmapFactory.decodeFile(imageFilePath,bitmapOptions)

                    val heightRatio = Math.ceil((bitmapOptions.outHeight/dh).toDouble())
                    val widthRatio = Math.ceil((bitmapOptions.outWidth/dw).toDouble())

                    if(heightRatio>1 && widthRatio > 1){
                        bitmapOptions.inSampleSize = heightRatio.toInt()
                    }else{
                        bitmapOptions.inSampleSize = widthRatio.toInt()
                    }
                    bitmapOptions.inJustDecodeBounds = false
                    bitmap = BitmapFactory.decodeFile(imageFilePath,bitmapOptions)

                   binding.returnedImageView.setImageBitmap(bitmap)
                }
            ///storage/FAD4-1EFB/DCIM/Camera/IMG_20210613_170306.jpg
        }
    }
    var imageFilePath:String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FragmentCameraIndexBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.demoName = item

        //        "{fa-github} Fork me on Github {fa-heart}"
        binding.setOnClickListener {
            val demoName = (it as BootstrapButton).text.toString()

            val bundle = Bundle()
            bundle.putString(ARG_ITEM_ID, demoName)

            when (it.id) {
                R.id.to_zxing -> {
                    val intent = Intent(requireContext(), CaptureActivity::class.java)
                    startActivity(intent)
//                    it.findNavControlle
                }
                R.id.to_camera -> {
                    imageFilePath = Environment.getExternalStorageDirectory().absolutePath+"/myfile.jpg"
                    val file = File(imageFilePath)
                    val imageUri = Uri.fromFile(file)
                    val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,imageUri)
                    //打开ActivityB
                    startActivitylaunch.launch(intent)

//                    it.findNavController().navigate(R.id.to_fragment_zxing_demo)
                }
                R.id.to_surface->{

                }
                R.id.to_select_picture->{
                    requestPermissions{

                        PictureSelector.create(this)
                            .openGallery(PictureMimeType.ofImage())
                            .compress(true)
                            .maxSelectNum(9)
                            .minSelectNum(1)
                            .imageSpanCount(4)
                            .selectionMode(PictureConfig.MULTIPLE)
                            .imageEngine(GlideEngine.createGlideEngine())
                            .forResult(PictureConfig.CHOOSE_REQUEST)
//                        PictureSelector.create(this)
//                            .openCamera(PictureMimeType.ofImage())
//                            .compress(true)
//                            .enableCrop(true)
//                            .showCropFrame(true)
//                            .showCropGrid(true)
//                            .scaleEnabled(true)
//                            .isDragFrame(true)
//                            .imageEngine(GlideEngine.createGlideEngine())
//                            .forResult(PictureConfig.CHOOSE_REQUEST)

                    }
                }
            }
        }

        return root
    }

    //请求权限sign_norm_activity
    fun requestPermissions( callBack:()->Unit) {
        XXPermissions.with(this)
            // 申请单个权限
            .permission(PermissionLists.getReadMediaAudioPermission())
            .permission(PermissionLists.getReadMediaImagesPermission())
            .permission(PermissionLists.getReadMediaVideoPermission())
            .permission(PermissionLists.getCameraPermission())
            .request(object : OnPermissionCallback {

                override fun onResult(
                    grantedList: List<IPermission?>,
                    deniedList: List<IPermission?>
                ) {
                    val allGranted = deniedList.isEmpty()
                    if (!allGranted) {
                        Logger.e("获取部分权限成功，但部分权限未正常授予")
                        return
                    } else{
                        Logger.e("获取权限成功")
                        callBack()
                    }

                }
            })
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layoutBind = null
    }
}