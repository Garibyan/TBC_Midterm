package com.garibyan.armen.tbc_midterm.network

import com.garibyan.armen.tbc_midterm.network.responcemodels.Category
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail
import com.garibyan.armen.tbc_midterm.network.responcemodels.Drinks
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular.php")
    suspend fun getPopularCocktails(): Drinks<Cocktail>

    @GET("latest.php")
    suspend fun getLatestCocktails(): Drinks<Cocktail>

    @GET("random.php")
    suspend fun getRandomCocktail(): Drinks<Cocktail>

    @GET("randomselection.php")
    suspend fun getTenRandomCocktails(): Drinks<Cocktail>

    @GET("lookup.php")
    suspend fun getCocktailById(@Query("i") i: String): Cocktail

    @GET("filter.php")
    suspend fun getCocktailByCategory(@Query("c") c: String): Drinks<Cocktail>


    @GET("list.php?c=list")
    suspend fun getCategories(): Drinks<Category>

//    @GET("list.php?i=list")
//    suspend fun getIngredients(): Cocktails
//
//    @GET("list.php?a=list")
//    suspend fun getAlcoholicFilters(): Cocktails
//
//    @GET("list.php?g=list")
//    suspend fun getGlasses(): Cocktails


}