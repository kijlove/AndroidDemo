package com.kijlee.android.demo.ui.compose.model

data class Module(
    val mid: String,
    val pid: String,
    val name: String,
    val userId: Int,
    val button: String? = null,
    val oid: Int? = null
)
