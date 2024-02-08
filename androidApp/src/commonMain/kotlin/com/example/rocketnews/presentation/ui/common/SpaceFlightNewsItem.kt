package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.formatRocketsDate
import com.example.rocketnews.presentation.theme.spacing
import com.example.rocketnews.presentation.ui.common.state.LoadingComponent
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@Composable
fun SpaceFlightNewsItem(
    spaceFlightNews: SpaceFlightNews,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = MaterialTheme.spacing.horizontal),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(Modifier.clickable(onClick = onClick)) {
            AutoSizeBox(spaceFlightNews.image_url) { action ->
                when (action) {
                    is ImageAction.Success -> {
                        Image(
                            rememberImageSuccessPainter(action),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().height(180.dp)
                        )
                    }

                    is ImageAction.Loading -> {
                        LoadingComponent(modifier = Modifier.fillMaxWidth().height(180.dp))
                    }

                    is ImageAction.Failure -> {}
                }
            }

            Column(Modifier.padding(8.dp)) {
                Space()
                Text(
                    text = spaceFlightNews.title,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Space()
                Text(
                    text = formatRocketsDate(spaceFlightNews.published_at),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Space(20.dp)
            }
        }
    }
}