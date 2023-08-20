package com.campodonico.ec_final.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "michi")
@Parcelize
data class Michi(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val character: String,
    val gameSeries: String,
    val image: String,
    val type: String,
    val tail: String,
    val amiiboSeries: String,
    var isFavorite: Boolean = false
):Parcelable
fun getData(): List<Michi> =

    listOf(
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),
        Michi(1,"", "Nike cupon", "Nike Store", "50", "Obten un 50% de descuento por dia del padre.", "24 June 2023", "24 June 2023",false),

        )