package com.griitingz.shopee.models

data class Data(
    val `data`: List<DataX>,
    val order_number: String,
    val session_name: String,
    val slug: String,
    val status: String,
    val title: String,
    val title_ar: String,
    val version: String,
    val view_style: String
)