package karel.hudera.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.helpers.formatRocketsDate
import karel.hudera.rocketnews.presentation.theme.spacing
import karel.hudera.rocketnews.presentation.ui.common.Space
import karel.hudera.rocketnews.presentation.ui.common.buttons.GoToArticleButton
import karel.hudera.rocketnews.presentation.ui.common.LoadingComponent
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@Composable
fun SpaceFlightNewsDetailScreenComponent(spaceFlightNew: SpaceFlightNews) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            AutoSizeBox(spaceFlightNew.image_url) { action ->
                when (action) {
                    is ImageAction.Success -> {
                        Image(
                            rememberImageSuccessPainter(action),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(horizontal = MaterialTheme.spacing.horizontal)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }

                    is ImageAction.Loading -> {
                        LoadingComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(horizontal = MaterialTheme.spacing.horizontal)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }

                    is ImageAction.Failure -> {}
                }
            }

            Column(Modifier.padding(16.dp)) {
                Text(
                    text = formatRocketsDate(spaceFlightNew.published_at),
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Space()
                Text(
                    text = spaceFlightNew.summary,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Space(120.dp)
            }
        }

        GoToArticleButton(
            spaceFlightNew = spaceFlightNew,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(16.dp))
                .padding(22.dp)
                .wrapContentWidth()
                .height(38.dp)
        )
    }
}