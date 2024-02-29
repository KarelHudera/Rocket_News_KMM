package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NoImageError(imageSize: Dp, padding: Dp) {
    Box(
        modifier = Modifier
            .padding(padding)
            .width(imageSize)
            .height(imageSize)
            .border(
                6.dp, Color.LightGray,
                shape = RoundedCornerShape(imageSize)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Mission patch",
            textAlign = TextAlign.Center,
            color = Color.LightGray
        )
    }
}
