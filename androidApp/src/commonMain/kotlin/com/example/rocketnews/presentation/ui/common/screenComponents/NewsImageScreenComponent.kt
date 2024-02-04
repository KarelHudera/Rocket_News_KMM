package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rocketnews.presentation.ui.common.ProgressIndicator
import com.seiko.imageloader.rememberImagePainter

@Composable
fun NewsImageScreenComponent(
    hdUrl: String,
    url: String
) {
    Box(Modifier.fillMaxSize()) {
        ProgressIndicator(Modifier.align(Alignment.Center))
        if (hdUrl == "") {
            Image(
                painter = rememberImagePainter(url),
                modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                contentDescription = null
            )
        } else {
            Image(
                painter = rememberImagePainter(hdUrl),
                modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                contentDescription = null
            )
        }
    }
}