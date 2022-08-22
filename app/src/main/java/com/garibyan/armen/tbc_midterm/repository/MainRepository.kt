package com.garibyan.armen.tbc_midterm.repository

import com.garibyan.armen.tbc_midterm.network.ApiService

class MainRepository(private val api: ApiService): BaseRepository() {

    suspend fun getPopularCocktails() = apiCall { api.getPopularCocktails() }

    suspend fun getCocktailById(id: String) = apiCall { api.getCocktailById(id)}

}