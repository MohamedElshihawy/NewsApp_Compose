package com.example.newsgate.news.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsApiResponseModel(
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    val articles: List<ArticleModel>
)
