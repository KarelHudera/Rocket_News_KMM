package com.example.rocketnews

import androidx.compose.runtime.Composable
import com.example.rocketnews.presentation.ui.screens.MainScreen
import com.example.rocketnews.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    MainScreen()
}

internal expect fun openUrl(url: String?)