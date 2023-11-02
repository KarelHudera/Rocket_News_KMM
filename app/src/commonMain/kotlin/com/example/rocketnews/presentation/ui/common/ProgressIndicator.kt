package com.example.rocketnews.presentation.ui.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProgressIndicator(modifier: Modifier) {
        CircularProgressIndicator(modifier, Color(0xFF434343))
}