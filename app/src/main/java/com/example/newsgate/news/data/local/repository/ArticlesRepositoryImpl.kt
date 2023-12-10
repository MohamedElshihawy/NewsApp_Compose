package com.example.newsgate.news.data.local.repository

import com.example.newsgate.news.data.local.appDatabase.ArticlesDao
import com.example.newsgate.news.data.local.entities.ArticleEntity
import com.example.newsgate.news.domain.repository.ArticlesRepository

class ArticlesRepositoryImpl(
    private val articlesDao: ArticlesDao,
) : ArticlesRepository {
    override fun getAllNormalArticles() = articlesDao.getAllNormalArticles()
    override fun getAllHeadlinesArticles(isHeadline: Boolean) =
        articlesDao.getHeadlinesArticles(isHeadline)

    override fun getArticleById(id: Long) = articlesDao.getArticleById(id)

    override fun insertArticles(articles: List<ArticleEntity>) =
        articlesDao.insertArticles(articles)

    override fun deleteAllArticles() = articlesDao.deleteAllArticles()
}
