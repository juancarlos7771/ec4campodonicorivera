package com.campodonico.ec_final.view.fragments

import android.app.Application
import androidx.lifecycle.*
import com.campodonico.ec_final.data.db.MichiDataBase
import com.campodonico.ec_final.data.repository.MichisRepository
import com.campodonico.ec_final.model.Michi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MichiFavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MichisRepository
    private val _favorites: MutableLiveData<List<Michi>> = MutableLiveData()
    val favorites: LiveData<List<Michi>> = _favorites

    init {
        val db = MichiDataBase.getDatabase(application)
        repository = MichisRepository(db.michiDao())
        refreshFavorites()
    }

    fun refreshFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = data
            }
        }
    }
}
