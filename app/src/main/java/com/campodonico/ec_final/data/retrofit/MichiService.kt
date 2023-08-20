package com.campodonico.ec_final.data.retrofit

import com.campodonico.ec_final.data.response.MichiListResponse
import retrofit2.http.GET

interface MichiService {
    @GET("amiibo/")
    suspend fun getCupons(): MichiListResponse

    @GET("amiibo/?tail=00040002")
    suspend fun getFavorite(): MichiListResponse
}