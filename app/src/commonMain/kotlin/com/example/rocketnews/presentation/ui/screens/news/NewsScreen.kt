package com.example.rocketnews.presentation.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.screens.rocketDetail.CharacterDetailScreen
import com.example.rocketnews.presentation.ui.screens.rockets.ActionAppBar
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsContract
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsViewModel
import com.example.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesScreen
import kotlinx.coroutines.flow.collectLatest

class NewsScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val rocketsViewModel = getScreenModel<RocketsViewModel>()

        val state by rocketsViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            rocketsViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is RocketsContract.Effect.NavigateToDetailCharacter ->
                        navigator.push(CharacterDetailScreen(effect.idRocket))

                    is RocketsContract.Effect.NavigateToFavorites ->
                        navigator.push(RocketsFavoritesScreen())
                }
            }
        }

        Scaffold(
            topBar = { ActionAppBar { rocketsViewModel.setEvent(RocketsContract.Event.OnFavoritesClick) } },
            floatingActionButton = {
                IconButton(
                    onClick = {},
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.DarkGray).size(58.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        modifier = Modifier.size(38.dp),
                        contentDescription = null
                    )
                }
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.characters,
                successView = {
                    Box(Modifier.fillMaxSize().background(Color.White))
                },
                onTryAgain = { rocketsViewModel.setEvent(RocketsContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsViewModel.setEvent(RocketsContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}