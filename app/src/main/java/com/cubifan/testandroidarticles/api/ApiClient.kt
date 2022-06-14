package com.cubifan.testandroidarticles.api

import com.cubifan.testandroidarticles.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL: String = BuildConfig.LINK_API

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient : OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        OkHttpClient
            .Builder()
            .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.NONE))
            .build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val API_SERVICE : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}