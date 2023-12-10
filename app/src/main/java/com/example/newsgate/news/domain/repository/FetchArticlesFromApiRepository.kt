package com.example.newsgate.news.domain.repository

import com.example.newsgate.news.domain.models.Article
import com.example.newsgate.news.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FetchArticlesFromApiRepository {

    fun getTopHeadLines(): Flow<Resource<List<Article>>>

    fun getEverything(): Flow<Resource<List<Article>>>

    fun getNewsByCategory(category: String): Flow<Resource<List<Article>>>

    fun searchNew(query: String): Flow<Resource<List<Article>>>
}
