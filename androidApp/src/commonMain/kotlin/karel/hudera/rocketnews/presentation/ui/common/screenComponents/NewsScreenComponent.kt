package karel.hudera.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.presentation.ui.common.BottomSheetContent
import karel.hudera.rocketnews.presentation.ui.common.LoadingComponent
import karel.hudera.rocketnews.presentation.ui.screens.news.NewsContract
import karel.hudera.rocketnews.presentation.ui.screens.news.NewsViewModel
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenComponent(
    news: News,
    newsViewModel: NewsViewModel
) {
    val sheetState = rememberModalBottomSheetState()

    val showNewsInfoDialog = newsViewModel.showNewsInfoDialog.collectAsState().value

    Box(
        Modifier.clickable { newsViewModel.setEvent(NewsContract.Event.OnImageClick) }
    ) {
        AutoSizeBox(news.url) { action ->
            when (action) {
                is ImageAction.Success -> {
                    Box {
                        Image(
                            rememberImageSuccessPainter(action),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Image(
                            rememberImagePainter(news.hdurl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                is ImageAction.Loading -> {
                    LoadingComponent(modifier = Modifier.fillMaxSize())
                }

                is ImageAction.Failure -> {}
            }
        }

        if (showNewsInfoDialog) {
            ModalBottomSheet(
                onDismissRequest = {
                    newsViewModel.setNewsInfoBottomSheet(false)
                },
                sheetState = sheetState,
                scrimColor = Color.Transparent,
                shape = RoundedCornerShape(16.dp, 16.dp),
                tonalElevation = 0.dp
            ) {
                BottomSheetContent(news)
            }
        }
    }
}