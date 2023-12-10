package com.example.newsgate.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsgate.news.presentaion.features.allNews.AllNewsScreen
import com.example.newsgate.news.presentaion.features.newsDetails.ArticleDetailsScreen

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    MyNavHost(navController)
}

@Composable
fun MyNavHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screens.AllNewsScreen.route) {
        composable(route = Screens.AllNewsScreen.route) {
            AllNewsScreen(navController = navController)
        }

        composable(
            route = Screens.NewsDetailsScreen.route + "/{article_id}",
            arguments = listOf(
                navArgument("article_id") {
                    type = NavType.LongType
                    defaultValue = 0L
                },
            ),
        ) { backStack ->
            val articleId = backStack.arguments?.getLong("article_id")!!
            ArticleDetailsScreen(articleId = articleId, navController = navController)
        }
    }
}
