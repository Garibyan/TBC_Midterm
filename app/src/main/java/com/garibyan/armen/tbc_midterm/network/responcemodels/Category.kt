package com.garibyan.armen.tbc_midterm.network.responcemodels

import com.squareup.moshi.Json

data class Category(
    @Json(name = "strCategory")
    val item: String
)
