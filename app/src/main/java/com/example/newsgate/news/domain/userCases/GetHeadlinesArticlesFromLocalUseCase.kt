package com.example.newsgate.news.domain.userCases

import com.example.newsgate.news.domain.repository.ArticlesRepository

class GetHeadlinesArticlesFromLocalUseCase(
    private val articlesRepository: ArticlesRepository,
) {

    operator fun invoke() = articlesRepository.getAllHeadlinesArticles(true)
}
