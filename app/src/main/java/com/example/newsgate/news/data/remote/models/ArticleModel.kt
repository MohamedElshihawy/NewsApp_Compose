package com.example.newsgate.news.data.remote.models

import com.example.newsgate.news.data.local.entities.ArticleEntity
import com.example.newsgate.news.domain.models.Article
import kotlinx.serialization.Serializable

@Serializable
data class ArticleModel(
    val source: SourceModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
) {
    fun mapToEntity() = ArticleEntity(
        sourceId = this.source?.id ?: "",
        sourceName = this.source?.name ?: "",
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        imageUrl = this.urlToImage ?: "",
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
    )

    fun mapToDomain() = Article(
        source = this.source?.mapToDomain() ?: SourceModel("", "").mapToDomain(),
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        imageUrl = this.urlToImage ?: "",
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
    )
}
