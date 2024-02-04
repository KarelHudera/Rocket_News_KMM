package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.helpers.formatRocketsDate
import com.example.rocketnews.presentation.ui.common.buttons.WikipediaButton
import com.example.rocketnews.presentation.ui.common.buttons.YoutubeButton
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel
import com.seiko.imageloader.rememberImagePainter

@Composable
fun RocketDetailScreenComponent(rocket: Rocket, rocketDetailViewModel: RocketDetailViewModel) {
    val imageSize = 300.dp

    val (text, color) = when (rocket.success) {
        true -> "Success" to Color.Green
        false -> "Fail" to Color.Red
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Space(52.dp)
        if (rocket.patchLarge == "" && rocket.patchSmall == "") {
            NoImageError(imageSize)
        } else {
            Box {
                ProgressIndicator(Modifier.align(Alignment.Center))
                Image(
                    modifier = Modifier.size(imageSize),
                    painter = rememberImagePainter(rocket.patchSmall),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(imageSize),
                    painter = rememberImagePainter(rocket.patchLarge),
                    contentDescription = null
                )
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
        Space(52.dp)
        Row {
            YoutubeButton(rocket, rocketDetailViewModel)
            Space(22.dp)
            WikipediaButton(rocket, rocketDetailViewModel)
        }
    }
}