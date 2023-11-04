package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
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
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun RocketDetail(rocket: Rocket) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = rocket.name,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.size(10.dp))
        if (rocket.patchLarge == "") {
            Box(
                modifier = Modifier.size(200.dp),
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
                    modifier = Modifier.size(200.dp),
                    painter = rememberAsyncImagePainter(rocket.patchSmall),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = rememberAsyncImagePainter(rocket.patchLarge),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "${formatDate(rocket.date_utc)}, ${rocket.name}",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(10.dp))
    }
}
