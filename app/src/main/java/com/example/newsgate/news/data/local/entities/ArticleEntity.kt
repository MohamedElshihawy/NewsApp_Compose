package com.example.newsgate.news.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsgate.news.data.remote.models.SourceModel
import com.example.newsgate.news.domain.models.Article

@Entity("articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val sourceId: String,
    val sourceName: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val isHeadline: Boolean = false,
) {

    fun mapToDomain() = Article(
        source = SourceModel(this.sourceId, this.sourceName).mapToDomain(),
        author = this.author,
        content = this.content,
        description = this.description,
        imageUrl = this.imageUrl,
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        id = this.id,
    )
}
