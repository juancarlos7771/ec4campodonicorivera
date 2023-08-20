package com.campodonico.ec_final.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://amiiboapi.com/api/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMichiService(): MichiService = retrofit.create(MichiService::class.java)
}