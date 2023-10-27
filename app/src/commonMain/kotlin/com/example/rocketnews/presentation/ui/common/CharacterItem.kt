package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun CharacterItem(
    character: Rocket,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box{
            CircularProgressIndicator(Modifier.align(Alignment.Center))
            Image(
                painter = rememberAsyncImagePainter(character.patchLarge),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp, bottom = 10.dp)
                    .width(110.dp)
                    .height(110.dp)
            )
        }
        Text(
            text = character.name,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

