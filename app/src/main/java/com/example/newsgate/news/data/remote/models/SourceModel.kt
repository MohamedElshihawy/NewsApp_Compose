package com.example.newsgate.news.data.remote.models

import com.example.newsgate.news.domain.models.Source
import kotlinx.serialization.Serializable

@Serializable
data class SourceModel(
    val id: String?,
    val name: String?,
) {
    fun mapToDomain() = Source(
        id = this.id ?: "",
        name = this.name ?: "",
    )
}
