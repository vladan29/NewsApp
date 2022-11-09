package com.vladan.newsapp.api

import com.vladan.newsapp.dtomodels.HeadlineDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("category") category: String,
        @Query("country") country: String
    ): Response<HeadlineDto>
}