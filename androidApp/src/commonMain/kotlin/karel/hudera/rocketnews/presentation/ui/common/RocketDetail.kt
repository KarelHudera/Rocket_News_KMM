package karel.hudera.rocketnews.presentation.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.helpers.formatRocketsDate
import karel.hudera.rocketnews.presentation.theme.spacing
import karel.hudera.rocketnews.presentation.ui.common.buttons.UrlButton
import karel.hudera.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RocketDetailScreenComponent(rocket: Rocket, rocketDetailViewModel: RocketDetailViewModel) {
    val imageSize = 300.dp

    val (text, color) = when (rocket.success) {
        true -> "Success" to Color.Green
        false -> "Fail" to Color.Red
    }

    val pagerState = rememberPagerState(pageCount = {
        rocket.flickr.size
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Space(52.dp)
        Box {
            AutoSizeBox(rocket.patchSmall) { action ->
                when (action) {
                    is ImageAction.Success -> {
                        Box {
                            Image(
                                painter = rememberImageSuccessPainter(action),
                                modifier = Modifier.size(imageSize),
                                contentDescription = null
                            )
                            Image(
                                painter = rememberImagePainter(rocket.patchLarge),
                                modifier = Modifier.size(imageSize),
                                contentDescription = null
                            )
                        }
                    }

                    is ImageAction.Loading -> {
                        Box(modifier = Modifier.size(imageSize)) {
                            ProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }

                    is ImageAction.Failure -> {
                        NoImageError(imageSize, 0.dp)
                    }
                }
            }
        }

        Space(52.dp)

        Text(
            text = "Launch: ${formatRocketsDate(rocket.date_utc)}",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            color = color
        )

        Space(22.dp)

        Card(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = MaterialTheme.spacing.horizontal
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                UrlButton(
                    rocket.webcast,
                    rocketDetailViewModel,
                    painterResource("youtube_ic.xml"),
                )
                UrlButton(
                    rocket.reddit,
                    rocketDetailViewModel,
                    painterResource("reddit_ic.xml"),
                )
                UrlButton(
                    rocket.wikipedia,
                    rocketDetailViewModel,
                    painterResource("wikipedia_ic.xml"),
                )
            }
        }

        Space(16.dp)

        Card(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = MaterialTheme.spacing.horizontal
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {
            Text(
                text = rocket.details,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Medium,
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Paragraph
                )
            )
        }

        Space(16.dp)

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(
                horizontal = 16.dp,
            ),
            pageSpacing = 8.dp
        ) { page ->
            rocket.flickr.getOrNull(page)?.let {
                AutoSizeBox(rocket.flickr.getOrNull(page)!!) { action ->
                    when (action) {
                        is ImageAction.Success -> {
                            Card(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 1.dp
                                )
                            ) {
                                Column {
                                    Image(
                                        rememberImageSuccessPainter(action),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(imageSize),
                                        contentScale = ContentScale.Crop
                                    )
                                    Row(
                                        Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Mission Flickr",
                                            modifier = Modifier.padding(
                                                horizontal = 8.dp,
                                                vertical = 16.dp
                                            ).align(Alignment.CenterVertically),
                                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                            fontWeight = FontWeight.Medium,
                                        )
                                    }
                                }
                            }
                        }

                        is ImageAction.Loading -> {
                            LoadingComponent(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(imageSize + 54.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }

                        is ImageAction.Failure -> {}
                    }
                }
            }
        }
        Space(32.dp)
    }
}