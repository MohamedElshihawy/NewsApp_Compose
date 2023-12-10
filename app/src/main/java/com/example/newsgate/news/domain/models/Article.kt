package com.example.newsgate.news.domain.models

import com.example.newsgate.news.data.local.entities.ArticleEntity
import com.example.newsgate.news.data.remote.models.SourceModel

data class Article(
    val id: Long? = null,
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val isHeadLine: Boolean = false,
) {
    fun mapToData() = ArticleEntity(
        sourceId = this.source.id,
        sourceName = this.source.name,
        author = this.author,
        content = this.content,
        description = this.description,
        imageUrl = this.imageUrl,
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        isHeadline = this.isHeadLine,
    )
}

data class Source(
    val id: String,
    val name: String,
) {

    fun mapToData() = SourceModel(
        id = this.id,
        name = this.name,
    )
}
