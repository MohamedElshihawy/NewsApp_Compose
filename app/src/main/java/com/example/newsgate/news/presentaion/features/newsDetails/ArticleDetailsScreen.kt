package com.example.newsgate.news.presentaion.features.newsDetails

import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.newsgate.news.navigation.Screens
import com.example.newsgate.news.presentaion.features.newsDetails.components.TopBar
import com.example.newsgate.news.utils.isNetworkAvailable
import org.koin.androidx.compose.get

@Composable
fun ArticleDetailsScreen(
    modifier: Modifier = Modifier,
    articleId: Long,
    viewModel: ArticleDetailsViewModel = get(),
    navController: NavController,
) {
    val article = viewModel.article.collectAsState()
    val context = LocalContext.current
    val isNetworkAvailable = remember { mutableStateOf(isNetworkAvailable(context)) }
    val shareLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
        }

    LaunchedEffect(key1 = Unit) {
        viewModel.getArticleById(articleId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TopBar(
            onBackClick = {
                navController.navigate(Screens.AllNewsScreen.route)
            },
            onShareClick = {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, article.value?.url)
                val intentChooser = Intent.createChooser(shareIntent, "Share article via")
                shareLauncher.launch(intentChooser)
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        article.value?.let {
            if (isNetworkAvailable.value) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
                            webViewClient = WebViewClient()
                        }
                    },
                    update = { webView ->
                        webView.loadUrl(it.url)
                    },
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Icon(
                        imageVector = Icons.Default.WifiOff,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(96.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Please connect to the internet and try again",
                        style = MaterialTheme.typography.displaySmall.copy(fontSize = 24.sp),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }

            //        article.value?.let {
//            ImageFromLink(
//                imageUrl = it.imageUrl,
//                modifier = Modifier.fillMaxWidth()
//                    .height(250.dp)
//                    .clip(RoundedCornerShape(24.dp)),
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = it.title,
//                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = "By ${it.author}",
//                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = it.content,
//                style = MaterialTheme.typography.bodyMedium,
//            )
//        }
//
//        if (article.value == null) {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Column {
//                    Icon(
//                        imageVector = Icons.Default.ErrorOutline,
//                        contentDescription = "Something went wrong",
//                        tint = MaterialTheme.colorScheme.error,
//                        modifier = Modifier.size(96.dp),
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    Text(
//                        text = "Something went wrong while fetching the article",
//                        color = MaterialTheme.colorScheme.error,
//                    )
//                }
//            }
//        }
        }
    }
}
