package com.example.newsgate.news.domain.userCases

import com.example.newsgate.news.domain.repository.ArticlesRepository

class ClearDatabaseUseCase(
    private val articlesRepository: ArticlesRepository,
) {
    suspend operator fun invoke() = articlesRepository.deleteAllArticles()
}
