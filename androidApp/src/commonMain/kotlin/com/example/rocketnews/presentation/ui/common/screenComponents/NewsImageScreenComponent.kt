package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import com.example.rocketnews.presentation.ui.common.ProgressIndicator
import com.seiko.imageloader.rememberImagePainter
import kotlin.math.max
import kotlin.math.min

@Composable
fun NewsImageScreenComponent(
    hdUrl: String,
    url: String
) {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val maxZoomOut = 1f
    val maxZoomIn = 5f
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        scale = max(maxZoomOut, min(maxZoomIn, scale))
        rotation += rotationChange
        offset += offsetChange
    }

    Box(
        Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offset.x, // TODO
                translationY = offset.y
            )
            .transformable(state = state)
            .fillMaxSize()
    ) {
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