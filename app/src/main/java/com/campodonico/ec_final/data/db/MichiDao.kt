package com.campodonico.ec_final.data.db

import androidx.room.*
import com.campodonico.ec_final.model.Michi

@Dao
interface MichiDao {
    @Query("SELECT * FROM michi")
    fun getFavorites(): List<Michi>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(michi: Michi)

    @Delete
    suspend fun removeFavorite(michi: Michi)
    // Otras consultas aquí
}
