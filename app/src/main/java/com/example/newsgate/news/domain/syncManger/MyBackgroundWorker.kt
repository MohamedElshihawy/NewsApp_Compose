package com.example.newsgate.news.domain.syncManger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.newsgate.news.domain.models.Article
import com.example.newsgate.news.domain.userCases.ClearDatabaseUseCase
import com.example.newsgate.news.domain.userCases.FetchEverythingFromApiUseCase
import com.example.newsgate.news.domain.userCases.FetchHeadlinesFromApiUseCase
import com.example.newsgate.news.domain.userCases.InsertArticlesUseCase
import com.example.newsgate.news.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyBackgroundWorker(context: Context, params: WorkerParameters) :
    Worker(context, params), KoinComponent {

    val clearNewsUseCase by inject<ClearDatabaseUseCase>()
    val fetchHeadlinesUseCase by inject<FetchHeadlinesFromApiUseCase>()
    val fetchEverythingUseCase by inject<FetchEverythingFromApiUseCase>()
    val storeNewsLocallyUseCase by inject<InsertArticlesUseCase>()

    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            clearNewsUseCase()
            fetchHeadLines()
            fetchEverything()
        }
        return Result.success()
    }

    private fun fetchHeadLines() {
        CoroutineScope(Dispatchers.IO).launch {
            fetchHeadlinesUseCase()
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            var headlines = result.data!!
                            headlines = headlines.map { it.copy(isHeadLine = true) }
                            storeNewsLocally(headlines)
                        }

                        is Resource.Error -> {
                        }
                    }
                }
        }
    }

    private fun fetchEverything() {
        CoroutineScope(Dispatchers.IO).launch {
            fetchEverythingUseCase()
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            storeNewsLocally(result.data!!)
                        }

                        is Resource.Error -> {}
                    }
                }
        }
    }

    private fun storeNewsLocally(articles: List<Article>) {
        CoroutineScope(Dispatchers.IO).launch {
            storeNewsLocallyUseCase(articles)
        }
    }
}
