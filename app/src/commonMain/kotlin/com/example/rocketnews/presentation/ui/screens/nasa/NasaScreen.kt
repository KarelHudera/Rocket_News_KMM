package com.example.rocketnews.presentation.ui.screens.nasa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsScreen

class NasaScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize().background(Color.Red))
    }
}