package com.example.newsgate.news.presentaion.features.allNews.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlin.math.min

@Composable
fun ImageFromLink(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RoundedCornerShape(24.dp),
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
    )

    val state = painter.state

    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f,
        label = "",
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = shape),
        contentAlignment = Alignment.Center,
    ) {
        if (state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        } else if (state is AsyncImagePainter.State.Error) {
            Icon(
                imageVector = Icons.Default.BrokenImage,
                contentDescription = "No image",
                modifier = Modifier.fillMaxSize(),
            )
        }
        Image(
            painter = painter,
            contentDescription = "article title",
            contentScale = contentScale,
            modifier = Modifier
                .fillMaxSize()
                .scale(.8f + (.2f * transition))
                .graphicsLayer { rotationX = (1f - transition) * 5f }
                .alpha(min(1f, transition / .2f)),
        )
    }
}
