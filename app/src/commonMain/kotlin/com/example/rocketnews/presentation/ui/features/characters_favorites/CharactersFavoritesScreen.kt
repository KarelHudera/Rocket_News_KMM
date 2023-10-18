package com.example.rocketnews.presentation.ui.features.characters_favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import daniel.avila.rnm.kmm.presentation.ui.common.ArrowBackIcon
import com.example.rocketnews.presentation.ui.common.CharactersList
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.features.character_detail.CharacterDetailScreen
import kotlinx.coroutines.flow.collectLatest

class CharactersFavoritesScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val rocketsFavoritesViewModel = getScreenModel<RocketsFavoritesViewModel>()

        val state by rocketsFavoritesViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            rocketsFavoritesViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is CharactersFavoritesContract.Effect.NavigateToDetailCharacter ->
                        navigator.push(CharacterDetailScreen(effect.idRocket))

                    CharactersFavoritesContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                ActionBar(onBackPressed = {
                    rocketsFavoritesViewModel.setEvent(
                        CharactersFavoritesContract.Event.OnBackPressed
                    )
                })
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.charactersFavorites,
                successView = { favorites ->
                    CharactersList(
                        characters = favorites,
                        onCharacterClick = { idCharacter ->
                            rocketsFavoritesViewModel.setEvent(
                                CharactersFavoritesContract.Event.OnCharacterClick(
                                    idCharacter
                                )
                            )
                        }
                    )
                },
                onTryAgain = { rocketsFavoritesViewModel.setEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsFavoritesViewModel.setEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
                msgCheckAgain = "You don't have favorite characters yet"
            )
        }
    }
}

@Composable
fun ActionBar(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Characters Favorites") },
        navigationIcon = { ArrowBackIcon(onBackPressed) }
    )
}