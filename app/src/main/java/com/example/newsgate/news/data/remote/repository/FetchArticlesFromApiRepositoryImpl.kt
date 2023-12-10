package com.example.newsgate.news.data.remote.repository

import com.example.newsgate.news.data.remote.models.NewsApiResponseModel
import com.example.newsgate.news.domain.repository.FetchArticlesFromApiRepository
import com.example.newsgate.news.utils.NewsApi.API_KEY
import com.example.newsgate.news.utils.NewsApi.EVERYTHING
import com.example.newsgate.news.utils.NewsApi.TOP_HEAD_LINES
import com.example.newsgate.news.utils.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.flow

class FetchArticlesFromApiRepositoryImpl(
    private val httpClient: HttpClient,
) : FetchArticlesFromApiRepository {
    override fun getTopHeadLines() = flow {
        try {
            val response = httpClient.get(TOP_HEAD_LINES) {
                contentType(ContentType.Application.Json)
                parameter("apiKey", API_KEY)
                parameter("sources", "bbc-news")
                parameter("pageSize", "10")
            }
            if (response.status.isSuccess()) {
                val articles = response.body<NewsApiResponseModel>()
                emit(Resource.Success(articles.articles.map { it.mapToDomain() }))
            } else {
                emit(Resource.Error(message = response.status.description))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getEverything() = flow {
        try {
            val response = httpClient.get(EVERYTHING) {
                contentType(ContentType.Application.Json)
                parameter("apiKey", API_KEY)
                parameter("sortBy", "popularity")
                parameter("language", "en")
                parameter("q", "sports")
                parameter("pageSize", "50")
            }
            if (response.status.isSuccess()) {
                val articles = response.body<NewsApiResponseModel>()
                emit(Resource.Success(articles.articles.map { it.mapToDomain() }))
            } else {
                emit(Resource.Error(message = response.status.description))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getNewsByCategory(category: String) = flow {
        try {
            val response = httpClient.get(TOP_HEAD_LINES) {
                contentType(ContentType.Application.Json)
                parameter("apiKey", API_KEY)
                parameter("category", category)
            }
            if (response.status.isSuccess()) {
                val articles = response.body<NewsApiResponseModel>()
                emit(Resource.Success(articles.articles.map { it.mapToDomain() }))
            } else {
                emit(Resource.Error(message = response.status.description))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun searchNew(query: String) = flow {
        try {
            val response = httpClient.get(TOP_HEAD_LINES) {
                contentType(ContentType.Application.Json)
                parameter("apiKey", API_KEY)
                parameter("q", query)
            }
            if (response.status.isSuccess()) {
                val articles = response.body<NewsApiResponseModel>()
                emit(Resource.Success(articles.articles.map { it.mapToDomain() }))
            } else {
                emit(Resource.Error(message = response.status.description))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}
