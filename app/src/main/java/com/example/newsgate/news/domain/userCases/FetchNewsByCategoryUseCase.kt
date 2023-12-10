package com.example.newsgate.news.domain.userCases

import com.example.newsgate.news.domain.repository.FetchArticlesFromApiRepository

class FetchNewsByCategoryUseCase(
    private val fetchArticlesFromApiRepository: FetchArticlesFromApiRepository,
) {
    operator fun invoke(category: String) =
        fetchArticlesFromApiRepository.getNewsByCategory(category)
}
