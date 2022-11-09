package com.vladan.newsapp.repositories

import com.vladan.newsapp.api.ApiHelper
import com.vladan.newsapp.dtomodels.HeadlineDto
import retrofit2.Response
import javax.inject.Inject

class PageDetailRepository @Inject constructor(
    private val remoteRepository: ApiHelper
) {
    suspend fun getHeadlines(category: String, country: String): Response<HeadlineDto> {
        return remoteRepository.getHeadlines(category, country)
    }
}