package com.campodonico.ec_final.data.repository

import com.campodonico.ec_final.data.db.MichiDao
import com.campodonico.ec_final.data.response.ApiResponse
import com.campodonico.ec_final.data.response.MichiListResponse
import com.campodonico.ec_final.data.retrofit.ServiceInstance
import com.campodonico.ec_final.model.Michi

class MichisRepository(val michiDao: MichiDao? = null) {
    suspend fun getCupons(): ApiResponse<MichiListResponse> {
        return try {
            val response = ServiceInstance.getMichiService().getCupons()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }

    suspend fun getCuponsFavorite(): ApiResponse<MichiListResponse> {
        return try {
            val response = ServiceInstance.getMichiService().getFavorite()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }

    suspend fun addFavorite(michi: Michi) {
        michiDao?.let {
            it.addFavorite(michi)
        }
    }

    suspend fun removeFavorite(michi: Michi) {
        michiDao?.let {
            it.removeFavorite(michi)
        }
    }

    fun getFavorites(): List<Michi> {
        michiDao?.let {
            return it.getFavorites()
        } ?: kotlin.run {
            return listOf()
        }
    }
}
