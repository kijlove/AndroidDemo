package com.kijlee.android.demo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.ActivityMainBinding

import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    var _binding: ActivityMainBinding? = null

    private val binding get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =  ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

}


