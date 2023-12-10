package com.example.newsgate.news.presentaion.features.allNews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsgate.news.data.local.MyPreferenceManager
import com.example.newsgate.news.domain.models.Article
import com.example.newsgate.news.domain.userCases.UseCasesWrapper
import com.example.newsgate.news.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllNewsViewModel(
    private val useCasesWrapper: UseCasesWrapper,
    private val myPreferenceManager: MyPreferenceManager,
) : ViewModel() {

    val articles = useCasesWrapper.getAllArticlesFromLocalStorageUseCase()
        .cachedIn(viewModelScope)
    private val _headlineArticles = MutableStateFlow<List<Article>>(emptyList())
    val headlineArticles: StateFlow<List<Article>> = _headlineArticles

    init {
        val isFirstTimeToOpenApp = myPreferenceManager.isFirstTimeLaunch()

        if (isFirstTimeToOpenApp) {
            viewModelScope.launch(Dispatchers.IO) {
                fetchHeadLines()
                fetchEverything()
            }
        }

        loadHeadlinesFromLocal()
    }

    private fun loadHeadlinesFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            useCasesWrapper.getAllHeadlinesArticlesFromLocalUseCase()
                .collect { articles ->
                    _headlineArticles.value = articles.map { it.mapToDomain() }
                }
        }
    }

    private fun fetchHeadLines() {
        viewModelScope.launch(Dispatchers.IO) {
            useCasesWrapper.fetchHeadlinesFromApiUseCase()
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
        viewModelScope.launch(Dispatchers.IO) {
            useCasesWrapper.fetchEverythingFromApiUseCase()
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            storeNewsLocally(result.data!!)
                            Log.e(
                                "TAG",
                                "fetchDataFromApi:normal news is here ${result.data.size} ",
                            )
                        }

                        is Resource.Error -> {
                            Log.e(
                                "TAG",
                                "fetchDataFromApi:normal news is here ${result.message} ",
                            )
                        }
                    }
                }
        }
    }

    private fun storeNewsLocally(articles: List<Article>) {
        viewModelScope.launch(Dispatchers.IO) {
            useCasesWrapper.insertArticlesUseCase(articles)
        }
    }
}
