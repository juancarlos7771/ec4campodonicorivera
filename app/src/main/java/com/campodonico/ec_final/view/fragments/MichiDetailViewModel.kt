package com.campodonico.ec_final.view.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campodonico.ec_final.data.db.MichiDataBase
import com.campodonico.ec_final.data.repository.MichisRepository
import com.campodonico.ec_final.model.Michi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MichiDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MichisRepository
    private val favoriteMichis: MutableSet<String> = mutableSetOf()

    init {
        val db = MichiDataBase.getDatabase(application)
        repository = MichisRepository(db.michiDao())
        // Llena el conjunto de favoritos al inicio
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = repository.getFavorites()
            favoriteMichis.addAll(favorites.map { it.name })
        }
    }

    fun addFavorite(michi: Michi) {
        if (!favoriteMichis.contains(michi.name)) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addFavorite(michi)
                favoriteMichis.add(michi.name)
            }
        }
    }

    fun removeFavorite(michi: Michi) {
        if (favoriteMichis.contains(michi.name)) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.removeFavorite(michi)
                favoriteMichis.remove(michi.name)
            }
        }
    }

    fun isFavorite(michi: Michi): Boolean {
        return michi.name in favoriteMichis
    }
}
