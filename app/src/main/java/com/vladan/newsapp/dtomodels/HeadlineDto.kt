package com.vladan.newsapp.dtomodels

import com.google.gson.annotations.SerializedName

data class HeadlineDto(
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("articles")
    var articles: ArrayList<ArticleDto>
)
