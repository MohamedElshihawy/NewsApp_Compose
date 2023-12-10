package com.example.newsgate.news.presentaion.features.allNews

sealed class AllNewsEvents {

    data object LoadedData : AllNewsEvents()

    class SearchNewsByCategory(val category: String) : AllNewsEvents()
}
