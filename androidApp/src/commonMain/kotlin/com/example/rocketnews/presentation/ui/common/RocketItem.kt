package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.helpers.formatRocketsDate
import com.example.rocketnews.presentation.theme.spacing
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@Composable
fun RocketItem(
    rocket: Rocket,
    onClick: () -> Unit,
) {
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
                        Box(
                            modifier = Modifier
                                .padding(21.dp)
                                .width(88.dp)
                                .height(88.dp)
                                .clip(RoundedCornerShape(64.dp))
                                .border(6.dp, Color.LightGray, shape = RoundedCornerShape(64.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Mission patch",
                                textAlign = TextAlign.Center,
                            )
                        }
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
                Row {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(18.dp)
                            .background(
                                if (rocket.success) {
                                    Color.Green.copy(0.7f)
                                } else {
                                    Color.Red.copy(0.7f)
                                }
                            )
                            .align(Alignment.CenterVertically)
                    )
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
}