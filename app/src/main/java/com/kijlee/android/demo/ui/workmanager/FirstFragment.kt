package com.kijlee.android.demo.ui.workmanager

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.work.*
import com.google.android.material.snackbar.Snackbar
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FragmentFirstBinding
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.startBtn.setOnClickListener {
            Snackbar.make(view, "开始任务", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
            doWork()
            show(this,binding.textviewFirst)
        }
        binding.cancelBtn.setOnClickListener {
            Snackbar.make(view, "结束任务", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
            cancelWork()
        }
        //再次进入直接根据id获取之前成功的内容
        show(this,binding.textviewFirst)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * 执行任务
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun doWork(){
        //创建单工作请求对象:OneTimeWorkRequest
        val worker =  OneTimeWorkRequest.Builder(RequestWorker :: class.java)
            //设置允许进行网络请求的标识
            .setInputData(Data.Builder().putBoolean("isRequest",true).build())
            .setConstraints(myConstraints)
            .build()
        //WorkManager执行任务
        WorkManager.getInstance(requireContext()).enqueue(worker)
        //用sharePreference保存任务ID
        val adRequestId = worker.id
        var arid by Preference(requireContext(),"requestId", "")
        arid = adRequestId.toString()
    }

    /**
     * 任务取消
     */
    fun cancelWork(){
        //获取用sharePreference保存的任务ID
        var arid by Preference(requireContext(),"requestId", "")
        if (!arid.equals("")) {
            val uuid = UUID.fromString(arid)
            WorkManager.getInstance(requireContext()).cancelWorkById(uuid)
        }
    }

    /**
     * 根据id,展示网络请求到的内容
     */
    fun show(owner: LifecycleOwner, view: TextView) {
        var arid by Preference(requireContext(),"requestId", "")
        if (!arid.equals("")) {
            val uuid = UUID.fromString(arid)
            //livedata实时监听任务状态
            WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(uuid)
                .observe(owner, {
                    if (it.state == WorkInfo.State.SUCCEEDED) {
                        val adResult = it.outputData.getString("data")
                        view.text = adResult
                    }
                }
                )
        }
    }

    //这是约束条件
    @RequiresApi(Build.VERSION_CODES.M)
    val myConstraints = Constraints.Builder()
        //网络连接时执行
        .setRequiredNetworkType(NetworkType.CONNECTED)
        // 在待机状态下执行，需要 API 23
//        .setRequiresDeviceIdle(true)
        // 在充电时执行
        .setRequiresCharging(true)
        .build()
}