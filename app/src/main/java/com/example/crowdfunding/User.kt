package com.example.crowdfunding

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes val pic: Array<Int>,
    val text: String,
    val amount: Long,
    val desc: String
)