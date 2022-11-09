package com.vladan.newsapp.dtomodels

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("source")
    var source: ArticleSourceDto,
    @SerializedName("author")
    var author: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("urlToImage")
    var urlToImage: String,
    @SerializedName("publishedAt")
    var publishedAt: String,
    @SerializedName("content")
    var content: String
)


data class ArticleSourceDto(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String
)