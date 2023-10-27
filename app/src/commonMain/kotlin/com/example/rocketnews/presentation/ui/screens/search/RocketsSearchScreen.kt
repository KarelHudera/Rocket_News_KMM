package com.example.rocketnews.presentation.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.presentation.ui.common.ArrowBackIcon
import com.example.rocketnews.presentation.ui.common.BackNavActionAppBar
import com.example.rocketnews.presentation.ui.common.CharactersList
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailScreen
import kotlinx.coroutines.flow.collectLatest

class RocketsSearchScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val rocketsSearchViewModel = getScreenModel<RocketsSearchViewModel>()

        val state by rocketsSearchViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        var text by remember { mutableStateOf("") }

        LaunchedEffect(key1 = Unit) {
            rocketsSearchViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is RocketsSearchContract.Effect.BackNavigation ->
                        navigator.pop()

                    is RocketsSearchContract.Effect.NavigateToDetailCharacter ->
                        navigator.push(RocketDetailScreen(effect.idRocket))
                }
            }
        }

        Scaffold(
            topBar = { BackNavActionAppBar(title = "Search Rockets",
                navIcon = {
                    ArrowBackIcon {
                        rocketsSearchViewModel.setEvent(
                            RocketsSearchContract.Event.OnBackPressed
                        )
                    }
                },) },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.rockets,
                successView = { rockets ->
                    Column {

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            placeholder = { Text(text = "Search rocket") },
                        )
                        CharactersList(
                            characters = rockets,
                            onCharacterClick = { idRocket ->
                                rocketsSearchViewModel.setEvent(
                                    RocketsSearchContract.Event.OnRocketClick(
                                        idRocket
                                    )
                                )
                            }
                        )
                    }
                },
                onTryAgain = { rocketsSearchViewModel.setEvent(RocketsSearchContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsSearchViewModel.setEvent(RocketsSearchContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

