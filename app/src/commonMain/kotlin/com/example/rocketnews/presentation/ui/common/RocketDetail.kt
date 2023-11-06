package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.helpers.formatRocketsDate
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun RocketDetail(rocket: Rocket) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Space(52.dp)
        if (rocket.patchLarge == "" && rocket.patchSmall == "") {
            Box(
                modifier = Modifier.size(300.dp),
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
        } else {
            Box {
                ProgressIndicator(Modifier.align(Alignment.Center))
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = rememberAsyncImagePainter(rocket.patchSmall),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = rememberAsyncImagePainter(rocket.patchLarge),
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
            style = MaterialTheme.typography.h6
        )
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = color
        )
//        Space(52.dp)
//        Row {
//            IconButton(
//                onClick = { rocket.youtube_id },
//                modifier = Modifier.clip(
//                    RoundedCornerShape(16.dp)
//                ).width(56.dp).height(38.dp).background(Color.Red),
//            ) {
//                Icon(
//                    imageVector = Icons.Rounded.PlayArrow,
//                    tint = Color.White,
//                    modifier = Modifier.size(26.dp),
//                    contentDescription = null
//                )
//            }
//            Space()
//            Button(
//                onClick = { rocket.wikipedia },
//                elevation = ButtonDefaults.buttonElevation(0.1.dp),
//                modifier = Modifier.clip(
//                    RoundedCornerShape(16.dp)
//                ).height(38.dp).wrapContentWidth(),
//                colors = ButtonDefaults.buttonColors(Color.White),
//            ) {
//                Text(
//                    "WIKI", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Medium
//                )
//            }
//        }
    }
}