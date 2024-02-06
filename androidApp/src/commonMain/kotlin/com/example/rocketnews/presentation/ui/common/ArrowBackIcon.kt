package com.example.rocketnews.presentation.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ArrowBackIcon(onBackPressed: () -> Unit) {
    IconButton(
        onClick = onBackPressed,
        colors = IconButtonDefaults.filledIconButtonColors(
            MaterialTheme.colorScheme.background.copy(
                0.3f
            )
        )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    }
}