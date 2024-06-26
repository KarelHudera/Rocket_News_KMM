package com.example.rocketnews.presentation.ui.screens.rocketsFavourite

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
import com.example.rocketnews.presentation.ui.common.screenComponents.RocketsFavoritesScreenComponent
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.common.topBars.BackNavActionAppBar
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailScreen
import kotlinx.coroutines.flow.collectLatest

class RocketsFavoritesScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val rocketsFavoritesViewModel = getScreenModel<RocketsFavoritesViewModel>()

        val state by rocketsFavoritesViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            rocketsFavoritesViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is RocketsFavoritesContract.Effect.NavigateToDetailRocket ->
                        navigator.push(RocketDetailScreen(effect.idRocket))

                    is RocketsFavoritesContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                BackNavActionAppBar(
                    title = "Favourite Rockets",
                    navIcon = {
                        ArrowBackIcon {
                            rocketsFavoritesViewModel.setEvent(
                                RocketsFavoritesContract.Event.OnBackPressed
                            )
                        }
                    },
                    isShadowEnabled = true
                )
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.rocketsFavorites,
                successView = { favorites ->
                    RocketsFavoritesScreenComponent(
                        favorites = favorites,
                        rocketsFavoritesViewModel = rocketsFavoritesViewModel
                    )
                },
                onTryAgain = { rocketsFavoritesViewModel.setEvent(RocketsFavoritesContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsFavoritesViewModel.setEvent(RocketsFavoritesContract.Event.OnTryCheckAgainClick) },
                msgCheckAgain = "You don't have favorite rocket yet"
            )
        }
    }
}