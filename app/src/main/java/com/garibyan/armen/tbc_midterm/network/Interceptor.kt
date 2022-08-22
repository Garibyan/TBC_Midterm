package com.garibyan.armen.tbc_midterm.network

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", "8657f4307amshd0542868659fe30p145abfjsn1d1682429733")
            .addHeader("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}