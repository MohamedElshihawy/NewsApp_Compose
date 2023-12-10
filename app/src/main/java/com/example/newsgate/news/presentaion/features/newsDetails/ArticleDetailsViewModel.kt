package com.example.newsgate.news.presentaion.features.newsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsgate.news.domain.models.Article
import com.example.newsgate.news.domain.userCases.GetArticleByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleDetailsViewModel(
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> = _article

    fun getArticleById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getArticleByIdUseCase(id)
                .first()
                .let {
                    withContext(Dispatchers.Main) {
                        _article.value = it.mapToDomain()
                    }
                }
        }
    }
}
