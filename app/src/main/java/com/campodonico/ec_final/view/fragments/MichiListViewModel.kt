package com.campodonico.ec_final.view.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campodonico.ec_final.data.repository.MichisRepository
import com.campodonico.ec_final.data.response.ApiResponse
import com.campodonico.ec_final.data.retrofit.MichiService
import com.campodonico.ec_final.model.Michi
import com.campodonico.ec_final.model.getData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MichiListViewModel : ViewModel() {
    val repository = MichisRepository()
    val cuponList: MutableLiveData<List<Michi>> = MutableLiveData<List<Michi>>()

    fun getCuponList(){
        val data = getData()
        cuponList.value = data
    }

    fun getCuponsFromService(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCupons()
            when(response){
                is ApiResponse.Error ->{
                    //colocar error
                }
                is ApiResponse.Success ->{
                    cuponList.postValue(response.data.amiibo)
                }
            }
        }
    }
    fun getCuponsFromServiceFa() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCuponsFavorite()
            when (response) {
                is ApiResponse.Error -> {
                    // Manejar el error
                }
                is ApiResponse.Success -> {
                    cuponList.postValue(response.data.amiibo)

                }
            }
        }
    }


}