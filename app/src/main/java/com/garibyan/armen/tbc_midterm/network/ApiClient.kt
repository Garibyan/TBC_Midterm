package com.garibyan.armen.tbc_midterm.network

import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktails
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    private const val BASE_URL = "https://the-cocktail-db.p.rapidapi.com/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(com.garibyan.armen.tbc_midterm.network.Interceptor())
    }.build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("popular.php")
    suspend fun getPopularCocktails(): Cocktails

    @GET("latest.php")
    suspend fun getLatestCocktails(): Cocktails

    @GET("random.php")
    suspend fun getRandomCocktail(): Cocktails

    @GET("randomselection.php")
    suspend fun getTenRandomCocktails(): Cocktails

    @GET("lookup.php")
    suspend fun getCocktailById(@Query("i") i: String): Cocktails




// TODO: implement those 4 methods in one
//    @GET("list.php?i=list")
//    suspend fun getIngredients(): Cocktails
//
//    @GET("list.php?a=list")
//    suspend fun getAlcoholicFilters(): Cocktails
//
//    @GET("list.php?g=list")
//    suspend fun getGlasses(): Cocktails
//
//    @GET("list.php?c=list")
//    suspend fun getCategories(): Cocktails

}