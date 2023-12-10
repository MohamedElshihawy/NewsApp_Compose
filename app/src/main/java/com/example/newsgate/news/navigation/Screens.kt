package com.example.newsgate.news.navigation

sealed class Screens(val route: String) {

    data object AllNewsScreen : Screens(route = "all_news_screen")

    data object NewsDetailsScreen : Screens(route = "news_details_screen")
}
