package com.vladan.newsapp.repositories

import com.vladan.newsapp.api.ApiHelper
import com.vladan.newsapp.api.ApiService
import com.vladan.newsapp.dtomodels.HeadlineDto
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getHeadlines(category: String, country: String): Response<HeadlineDto> {
        return apiService.getHeadlines(category, country)
    }
}