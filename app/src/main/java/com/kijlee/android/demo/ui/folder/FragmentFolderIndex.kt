package com.kijlee.android.demo.ui.folder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FragmentFolderIndexBinding
import com.kijlee.android.demo.ui.main.FgMain
import com.obsez.android.lib.filechooser.ChooserDialog


/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.folder
 * @ClassName:      FragmentFolderIndex
 * @Author:     kij
 * @Description:  文件夹列表
 * @Date:    2023/2/24 14:04
 * @Version:    1.0
 */
class FragmentFolderIndex  : Fragment() {
    var _binding: FragmentFolderIndexBinding? = null
    private val binding get() = _binding!!
    var item = ""
    var fileAdapter :FileListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(FgMain.Item_Id)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgMain.Item_Id).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFolderIndexBinding.inflate(layoutInflater)
        super.onCreateView(inflater, container, savedInstanceState)
        val root: View = binding.root
        onClick()
        initView()

        return root
    }

    fun initView() {
        val fileListStr:MutableList<String> = ArrayList<String>()
        for(item in 0..100){
            fileListStr.add("文件$item")
        }

        fileAdapter = FileListAdapter(R.layout.re_file,fileListStr)
        binding.folderName.setText("文件夹列表")
        binding.folderList.adapter = fileAdapter
        selectFolder()
    }

    fun onClick() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun selectFolder(){
       ChooserDialog().with(requireContext())
            .withFilter(true, false)
            .withStartFile("/storage/emulated")
            .withChosenListener(ChooserDialog.Result { path, pathFile ->
                Toast.makeText(
                    requireContext(),
                    "FOLDER: $path",
                    Toast.LENGTH_SHORT
                ).show()
            })
            .build()
            .show()
    }

}