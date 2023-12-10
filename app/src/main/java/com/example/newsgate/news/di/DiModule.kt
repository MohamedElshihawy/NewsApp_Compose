package com.example.newsgate.news.di

import androidx.work.WorkManager
import com.example.newsgate.news.data.local.MyPreferenceManager
import com.example.newsgate.news.data.local.appDatabase.MyDatabase
import com.example.newsgate.news.data.local.repository.ArticlesRepositoryImpl
import com.example.newsgate.news.data.remote.repository.FetchArticlesFromApiRepositoryImpl
import com.example.newsgate.news.domain.repository.ArticlesRepository
import com.example.newsgate.news.domain.repository.FetchArticlesFromApiRepository
import com.example.newsgate.news.domain.userCases.ClearDatabaseUseCase
import com.example.newsgate.news.domain.userCases.FetchEverythingFromApiUseCase
import com.example.newsgate.news.domain.userCases.FetchHeadlinesFromApiUseCase
import com.example.newsgate.news.domain.userCases.FetchNewsByCategoryUseCase
import com.example.newsgate.news.domain.userCases.GetAllArticlesFromLocalStorageUseCase
import com.example.newsgate.news.domain.userCases.GetArticleByIdUseCase
import com.example.newsgate.news.domain.userCases.GetHeadlinesArticlesFromLocalUseCase
import com.example.newsgate.news.domain.userCases.InsertArticlesUseCase
import com.example.newsgate.news.domain.userCases.UseCasesWrapper
import com.example.newsgate.news.presentaion.features.allNews.AllNewsViewModel
import com.example.newsgate.news.presentaion.features.newsDetails.ArticleDetailsViewModel
import com.example.newsgate.news.utils.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diModule = module {

    single {
        MyDatabase.getDatabase(androidContext())
    }

    single {
        get<MyDatabase>().articlesDao()
    }

    single {
        HttpClient.httpClientAndroid()
    }

    single<ArticlesRepository> {
        ArticlesRepositoryImpl(get())
    }

    single<FetchArticlesFromApiRepository> {
        FetchArticlesFromApiRepositoryImpl(get())
    }

    single {
        ClearDatabaseUseCase(get())
    }

    single {
        FetchEverythingFromApiUseCase(get())
    }

    single {
        FetchHeadlinesFromApiUseCase(get())
    }

    single {
        FetchNewsByCategoryUseCase(get())
    }

    single {
        GetAllArticlesFromLocalStorageUseCase(get())
    }

    single {
        GetArticleByIdUseCase(get())
    }

    single {
        InsertArticlesUseCase(get())
    }

    single {
        GetHeadlinesArticlesFromLocalUseCase(get())
    }

    single {
        MyPreferenceManager(androidContext())
    }

    single { WorkManager.getInstance(androidContext()) }

    single {
        UseCasesWrapper(get(), get(), get(), get(), get(), get(), get(), get())
    }

    viewModel {
        AllNewsViewModel(get(), get())
    }

    viewModel {
        ArticleDetailsViewModel(get())
    }
}
