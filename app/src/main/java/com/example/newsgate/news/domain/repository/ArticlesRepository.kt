package com.example.newsgate.news.domain.repository

import androidx.paging.PagingSource
import com.example.newsgate.news.data.local.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getAllNormalArticles(): PagingSource<Int, ArticleEntity>
    fun getAllHeadlinesArticles(isHeadline: Boolean): Flow<List<ArticleEntity>>
    fun getArticleById(id: Long): Flow<ArticleEntity>
    fun insertArticles(articles: List<ArticleEntity>)
    fun deleteAllArticles()
}
