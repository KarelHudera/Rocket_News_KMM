package com.example.rocketnews

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.rocketnews.presentation.ui.features.characters.CharactersScreen
import com.example.rocketnews.presentation.ui.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    Navigator(CharactersScreen())
}

