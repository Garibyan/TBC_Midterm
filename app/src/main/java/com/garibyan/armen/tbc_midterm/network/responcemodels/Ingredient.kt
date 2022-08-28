package com.garibyan.armen.tbc_midterm.network.responcemodels

import com.squareup.moshi.Json

data class Ingredient(
    @Json(name = "strIngredient1")
    val item: String
)
