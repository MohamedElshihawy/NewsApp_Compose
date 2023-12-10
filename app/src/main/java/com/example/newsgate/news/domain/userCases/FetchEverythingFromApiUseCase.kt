package com.example.newsgate.news.domain.userCases

import com.example.newsgate.news.domain.repository.FetchArticlesFromApiRepository

class FetchEverythingFromApiUseCase(
    private val fetchArticlesFromApiRepository: FetchArticlesFromApiRepository,
) {
    operator fun invoke() = fetchArticlesFromApiRepository.getEverything()
}
