package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.helpers.formatRocketsDate
import com.example.rocketnews.openUrl
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailContract
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel
import com.seiko.imageloader.rememberImagePainter

@Composable
fun RocketDetail(rocket: Rocket, rocketDetailViewModel: RocketDetailViewModel) {
    val imageSize = 300.dp

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
        val (text, color) = when (rocket.success) {
            true -> "Success" to Color.Green
            false -> "Fail" to Color.Red
        }
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

@Composable
fun NoImageError(imageSize: Dp) {
    Box(
        modifier = Modifier.size(imageSize),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                imageVector = Icons.Rounded.Warning,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentDescription = null
            )
            Space()
            Text("No image")
        }
    }
}

@Composable
fun WikipediaButton(rocket: Rocket, rocketDetailViewModel: RocketDetailViewModel) {
    Button(
        onClick = {
            if (rocket.wikipedia != "") {
                openUrl(rocket.wikipedia)
            } else {
                rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnEmptyUrlClick)
            }
        },
        elevation = ButtonDefaults.buttonElevation(1.dp),
        modifier = Modifier.clip(
            RoundedCornerShape(16.dp)
        ).height(38.dp).wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(Color.White),
    ) {
        Text(
            "WIKI",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun YoutubeButton(rocket: Rocket, rocketDetailViewModel: RocketDetailViewModel) {
    IconButton(
        onClick = {
            if (rocket.webcast != "") {
                openUrl(rocket.webcast)
            } else {
                rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnEmptyUrlClick)
            }
        },
        modifier = Modifier.clip(
            RoundedCornerShape(16.dp)
        ).width(56.dp).height(38.dp).background(Color.Red),
    ) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            modifier = Modifier.size(26.dp),
            tint = Color.White,
            contentDescription = null
        )
    }
}
