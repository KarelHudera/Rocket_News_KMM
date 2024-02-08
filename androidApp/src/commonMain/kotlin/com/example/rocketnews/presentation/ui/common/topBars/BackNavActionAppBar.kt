package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackNavActionAppBar(
    navIcon: @Composable () -> Unit,
    title: String,
    isShadowEnabled: Boolean = false
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navIcon,
        modifier = if (isShadowEnabled) {
            Modifier.shadow(2.dp)
        } else {
            Modifier
        }
    )
}