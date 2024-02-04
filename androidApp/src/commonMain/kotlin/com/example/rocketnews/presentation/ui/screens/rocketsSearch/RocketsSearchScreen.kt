package com.example.rocketnews.presentation.ui.screens.rocketsSearch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.example.rocketnews.presentation.ui.common.ArrowBackIcon
import com.example.rocketnews.presentation.ui.common.screenComponents.RocketsSearchScreenComponent
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.common.topBars.BackNavActionAppBar
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailScreen
import kotlinx.coroutines.flow.collectLatest

class RocketsSearchScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val rocketsSearchViewModel = getScreenModel<RocketsSearchViewModel>()

        val state by rocketsSearchViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            rocketsSearchViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is RocketsSearchContract.Effect.NavigateToDetailRocket ->
                        navigator.push(RocketDetailScreen(effect.idRocket))

                    is RocketsSearchContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                BackNavActionAppBar(
                    title = "Search Rockets",
                    navIcon = {
                        ArrowBackIcon {
                            rocketsSearchViewModel.setEvent(
                                RocketsSearchContract.Event.OnBackPressed
                            )
                        }
                    },
                )
            },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.filteredRockets,
                successView = { rockets ->
                    RocketsSearchScreenComponent(
                        rockets = rockets,
                        rocketsSearchViewModel = rocketsSearchViewModel
                    )
                },
                onTryAgain = { rocketsSearchViewModel.setEvent(RocketsSearchContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsSearchViewModel.setEvent(RocketsSearchContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}