package com.example.newsgate.news.presentaion.features.allNews.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsgate.news.domain.models.Article

@Composable
fun HeadlineArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onItemClick: (Long?) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(.95f)
            .clickable {
                onItemClick(article.id)
            },
        contentAlignment = Alignment.BottomCenter,
    ) {
        ImageFromLink(imageUrl = article.imageUrl, modifier = Modifier.fillMaxSize())

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(
                text = article.source.name ?: "Unknown",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.source.name ?: "Unknown",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
            )
        }
    }
}
