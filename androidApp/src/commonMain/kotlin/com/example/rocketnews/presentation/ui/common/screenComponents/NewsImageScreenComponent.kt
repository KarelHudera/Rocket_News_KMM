package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.rocketnews.presentation.ui.common.ProgressIndicator
import com.example.rocketnews.presentation.ui.common.ZoomableBox
import com.example.rocketnews.presentation.ui.common.LoadingComponent
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@Composable
fun NewsImageScreenComponent(
    hdUrl: String,
    url: String
) {
    ZoomableBox {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ),
        ) {
            ProgressIndicator(Modifier.align(Alignment.Center))
            if (hdUrl == "") {
                AutoSizeBox(url) { action ->
                    when (action) {
                        is ImageAction.Success -> {
                            Image(
                                rememberImageSuccessPainter(action),
                                modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                                contentDescription = null
                            )
                        }

                        is ImageAction.Loading -> {
                            LoadingComponent(modifier = Modifier.align(Alignment.Center).fillMaxWidth().height(600.dp))
                        }

                        is ImageAction.Failure -> {}
                    }
                }
            } else {
                AutoSizeBox(hdUrl) { action ->
                    when (action) {
                        is ImageAction.Success -> {
                            Image(
                                rememberImageSuccessPainter(action),
                                modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                                contentDescription = null
                            )
                        }

                        is ImageAction.Loading -> {
                            LoadingComponent(modifier = Modifier.align(Alignment.Center).fillMaxWidth().height(600.dp))
                        }

                        is ImageAction.Failure -> {}
                    }
                }
            }
        }
    }
}