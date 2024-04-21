package karel.hudera.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.helpers.formatRocketsDate
import karel.hudera.rocketnews.presentation.theme.spacing
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@Composable
fun RocketItem(
    rocket: Rocket,
    onClick: () -> Unit,
) {
    val (text, color) = when (rocket.success) {
        true -> "Mission Successful" to Color.Green
        false -> "Mission Failed" to Color.Red
    }

    Card(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = MaterialTheme.spacing.horizontal),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
        ) {
            AutoSizeBox(rocket.patchSmall) { action ->
                when (action) {
                    is ImageAction.Success -> {
                        Box {
                            Image(
                                painter = rememberImageSuccessPainter(action),
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp).width(110.dp).height(110.dp)
                            )

                            Image(
                                painter = rememberImagePainter(rocket.patchLarge),
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp).width(110.dp).height(110.dp)
                            )
                        }
                    }

                    is ImageAction.Loading -> {
                        Box(
                            modifier = Modifier.padding(10.dp).width(110.dp).height(110.dp)
                        ) {
                            ProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }

                    is ImageAction.Failure -> {
                        NoImageError(88.dp, 21.dp)
                    }
                }
            }

            Space()

            Column {
                Text(
                    text = rocket.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                )

                Space(4.dp)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(18.dp)
                            .background(color)
                            .align(Alignment.CenterVertically)
                    )

                    Space()

                    Text(
                        text = text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = color
                    )
                }

                Space()

                Text(
                    text = formatRocketsDate(rocket.date_utc),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}