package com.vladan.newsapp.api

import com.vladan.newsapp.dtomodels.HeadlineDto
import retrofit2.Response

interface ApiHelper {
    suspend fun getHeadlines(
        category: String,
        country: String
    ): Response<HeadlineDto>
}