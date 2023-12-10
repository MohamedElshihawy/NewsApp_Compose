package com.example.newsgate.news.domain.userCases

import com.example.newsgate.news.domain.models.Article
import com.example.newsgate.news.domain.repository.ArticlesRepository

class InsertArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
) {
    suspend operator fun invoke(articles: List<Article>) =
        articlesRepository.insertArticles(articles.map { it.mapToData() })
}
