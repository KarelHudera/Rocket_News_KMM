package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

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
