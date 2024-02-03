package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.formatRocketsDate
import com.example.rocketnews.presentation.ui.common.ProgressIndicator
import com.example.rocketnews.presentation.ui.common.Space
import com.example.rocketnews.presentation.ui.common.buttons.GoToArticleButton
import com.seiko.imageloader.rememberImagePainter

@Composable
fun SpaceFlightNewsDetailScreenComponent(spaceFlightNew: SpaceFlightNews) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box {
                Box(Modifier.fillMaxSize().background(Color.LightGray))

                ProgressIndicator(Modifier.align(Alignment.Center))

                Image(
                    painter = rememberImagePainter(spaceFlightNew.image_url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                )
            }

            Column(Modifier.padding(8.dp)) {
                Space()
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
            modifier = Modifier.padding(bottom = 24.dp).align(Alignment.BottomCenter).clip(
                RoundedCornerShape(16.dp)
            ).height(38.dp).wrapContentWidth()
        )
    }
}