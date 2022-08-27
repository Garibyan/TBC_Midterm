package com.garibyan.armen.tbc_midterm.repository

import com.garibyan.armen.tbc_midterm.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService): BaseRepository() {

    suspend fun getPopularCocktails() = apiCall { apiService.getPopularCocktails() }

    suspend fun getLatestCocktails() = apiCall { apiService.getLatestCocktails() }

    suspend fun getRandomTenCocktails() = apiCall { apiService.getTenRandomCocktails() }
    suspend fun getAllCategories() = apiCall { apiService.getCategories() }

    suspend fun getCocktailsByCategory(category: String) = apiCall { apiService.getCocktailByCategory(category) }

    suspend fun getCocktailById(id: String) = apiCall { apiService.getCocktailById(id)}


}