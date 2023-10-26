package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import com.example.rocketnews.domain.model.News
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun NewsComponent(news: News) {
    Column {
        Image(
            modifier = Modifier.fillMaxSize().scale(LocalDensity.current.density),
            painter = rememberAsyncImagePainter(news.hdurl),
            contentDescription = null
        )
    }
}