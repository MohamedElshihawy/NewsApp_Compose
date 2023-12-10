package com.example.newsgate.news.domain.userCases

import com.example.newsgate.news.domain.repository.ArticlesRepository

class GetArticleByIdUseCase(
    private val articlesRepository: ArticlesRepository,
) {
    suspend operator fun invoke(id: Long) = articlesRepository.getArticleById(id)
}
