package com.example.rocketnews.presentation.ui.screens.rockets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.presentation.ui.common.CharactersList
import com.example.rocketnews.presentation.ui.common.RocketsActionAppBar
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.screens.rocketDetail.CharacterDetailScreen
import com.example.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesScreen
import kotlinx.coroutines.flow.collectLatest

class RocketsScreen : Screen {
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

                    is RocketsContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                RocketsActionAppBar(
                    title = "SpaceX Rockets",
                    onClickFavorite = { rocketsViewModel.setEvent(RocketsContract.Event.OnFavoritesClick) },
                    onBackPressed = { rocketsViewModel.setEvent(RocketsContract.Event.OnBackPressed) }
                )
            },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.characters,
                successView = { characters ->
                    CharactersList(
                        characters = characters,
                        onCharacterClick = { idRocket ->
                            rocketsViewModel.setEvent(
                                RocketsContract.Event.OnCharacterClick(
                                    idRocket
                                )
                            )
                        }
                    )
                },
                onTryAgain = { rocketsViewModel.setEvent(RocketsContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsViewModel.setEvent(RocketsContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}