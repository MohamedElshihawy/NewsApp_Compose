package com.example.newsgate.news.domain.userCases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.newsgate.news.domain.models.Article
import com.example.newsgate.news.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllArticlesFromLocalStorageUseCase(
    private val articlesRepository: ArticlesRepository,
) {
    operator fun invoke(): Flow<PagingData<Article>> = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 20,
        ),
    ) {
        articlesRepository.getAllNormalArticles()
    }.flow
        .map { pagerValue ->
            pagerValue.map { articleEntity ->
                articleEntity.mapToDomain()
            }
        }
}
