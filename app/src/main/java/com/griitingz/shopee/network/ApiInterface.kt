package com.griitingz.shopee.network

import com.griitingz.shopee.models.HomeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiInterface {
    @Headers("Authorization: Basic UWFydEFQSTo4NTUyMjFBS1NKTkRLU1dPPEE6JigpIyMpQClAKSE3ODQxMjIxMTI=","Content-type:application/json")
    @GET("home_page_customization")
    fun getData(): Call<HomeModel>
}