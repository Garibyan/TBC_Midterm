package com.garibyan.armen.tbc_midterm.network.responcemodels

import com.squareup.moshi.Json

data class Cocktail(
    val idDrink: String? = null,
    @Json(name = "strDrink")
    val name: String? = null,
    val strCategory: String? = null,
    val strAlcoholic: String? = null,
    val strGlass: String? = null,
    val strInstructions: String? = null,
    val strImageSource: String? = null
)