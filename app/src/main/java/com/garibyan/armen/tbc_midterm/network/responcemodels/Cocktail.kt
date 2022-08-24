package com.garibyan.armen.tbc_midterm.network.responcemodels

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cocktail(
    val idDrink: String? = null,
    @Json(name = "strDrink")
    val name: String? = null,
    val strCategory: String? = null,
    val strAlcoholic: String? = null,
    val strGlass: String? = null,
    val strInstructions: String? = null,
    val strDrinkThumb: String? = null
): Parcelable