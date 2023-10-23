package com.example.rocketnews.presentation.ui.screens.rockets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.presentation.ui.common.ActionBarIcon
import com.example.rocketnews.presentation.ui.common.CharactersList
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.screens.rocketDetail.CharacterDetailScreen
import com.example.rocketnews.presentation.ui.screens.rocketsFavourite.CharactersFavoritesScreen
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
                    is CharactersContract.Effect.NavigateToDetailCharacter ->
                        navigator.push(CharacterDetailScreen(effect.idRocket))

                    is CharactersContract.Effect.NavigateToFavorites ->
                        navigator.push(CharactersFavoritesScreen())
                }
            }
        }

        Scaffold(
            topBar = { ActionAppBar { rocketsViewModel.setEvent(CharactersContract.Event.OnFavoritesClick) } }
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
                                CharactersContract.Event.OnCharacterClick(
                                    idRocket
                                )
                            )
                        }
                    )
                },
                onTryAgain = { rocketsViewModel.setEvent(CharactersContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsViewModel.setEvent(CharactersContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

@Composable
fun ActionAppBar(
    onClickFavorite: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Rockets goes brrr") },
        actions = {
            ActionBarIcon(
                onClick = onClickFavorite,
                icon = Icons.Filled.Favorite
            )
        }
    )
}


