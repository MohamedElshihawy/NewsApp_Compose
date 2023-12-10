package com.example.newsgate.news.presentaion.features.allNews

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.newsgate.R
import com.example.newsgate.news.navigation.Screens
import com.example.newsgate.news.presentaion.features.allNews.components.HeadlineArticleItem
import com.example.newsgate.news.presentaion.features.allNews.components.NormalArticleItem
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: AllNewsViewModel = get(),
    navController: NavController,
) {
    val articles = viewModel.articles.collectAsLazyPagingItems()
    val headlinesArticles = viewModel.headlineArticles.collectAsState()
    val columnState = rememberLazyListState()
    val pagerState = rememberPagerState {
        headlinesArticles.value.size
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(headlinesArticles.value.isNotEmpty()) {
            Column {
                HorizontalPager(
                    contentPadding = PaddingValues(8.dp),
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(.3f),
                ) {
                    val headline = headlinesArticles.value[it]
                    HeadlineArticleItem(
                        article = headline,
                    ) { id ->
                        navController.navigate(Screens.NewsDetailsScreen.route + "/$id")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    headlinesArticles.value.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index == pagerState.currentPage) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        Color.LightGray
                                    },
                                ),
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recommendations",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            state = columnState,
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(
                count = articles.itemCount,
                key = articles.itemKey { article -> article.id!! },
                contentType = articles.itemContentType { "NewsArticles" },
            ) { index ->
                val article = articles[index]
                article?.let {
                    NormalArticleItem(
                        article = it,
                        modifier = Modifier.height(160.dp),
                        onItemClick = { id ->
                            navController.navigate(Screens.NewsDetailsScreen.route + "/$id")
                        },
                    )
                }
                Divider(thickness = 1.dp, color = Color.LightGray)
            }
        }
    }
}
