package com.example.newsgate.news.domain.userCases

data class UseCasesWrapper(
    val clearDatabaseUseCase: ClearDatabaseUseCase,
    val fetchEverythingFromApiUseCase: FetchEverythingFromApiUseCase,
    val fetchNewsByCategoryUseCase: FetchNewsByCategoryUseCase,
    val fetchHeadlinesFromApiUseCase: FetchHeadlinesFromApiUseCase,
    val getAllArticlesFromLocalStorageUseCase: GetAllArticlesFromLocalStorageUseCase,
    val getArticleByIdUseCase: GetArticleByIdUseCase,
    val insertArticlesUseCase: InsertArticlesUseCase,
    val getAllHeadlinesArticlesFromLocalUseCase: GetHeadlinesArticlesFromLocalUseCase
)
