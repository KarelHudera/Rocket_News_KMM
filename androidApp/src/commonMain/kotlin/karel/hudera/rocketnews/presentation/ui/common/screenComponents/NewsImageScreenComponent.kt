package karel.hudera.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import karel.hudera.rocketnews.presentation.ui.common.LoadingComponent
import karel.hudera.rocketnews.presentation.ui.common.ZoomableBox
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
                )
        ) {
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
                            LoadingComponent(
                                modifier = Modifier.align(Alignment.Center).fillMaxSize()
                            )
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
                            LoadingComponent(
                                modifier = Modifier.align(Alignment.Center).fillMaxSize()
                            )
                        }

                        is ImageAction.Failure -> {}
                    }
                }
            }
        }
    }
}