package com.cubifan.testandroidarticles.api

import com.cubifan.testandroidarticles.api.responses.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("news.json")
    suspend fun getNews(): Response<NewsResponse>
}