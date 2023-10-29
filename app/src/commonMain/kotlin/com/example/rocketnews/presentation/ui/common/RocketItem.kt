package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun RocketItem(
    rocket: Rocket,
    onClick: () -> Unit
) {
    Card(
        Modifier.padding(vertical =8.dp , horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onClick)
        ) {
            Box{
                CircularProgressIndicator(Modifier.align(Alignment.Center))
                Image(
                    painter = rememberAsyncImagePainter(rocket.patchSmall),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(110.dp)
                        .height(110.dp)
                )
                Image(
                    painter = rememberAsyncImagePainter(rocket.patchLarge),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(110.dp)
                        .height(110.dp)
                )
            }
            Text(
                text = rocket.name,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}

