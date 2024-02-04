package com.example.rocketnews.presentation.ui.screens.spaceFlightNewsSearch

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
import com.example.rocketnews.presentation.ui.common.screenComponents.SpaceFlightNewsSearchScreenComponent
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.common.topBars.BackNavActionAppBar
import com.example.rocketnews.presentation.ui.screens.spaceFlightNewsDetail.SpaceFlightNewsDetailScreen
import kotlinx.coroutines.flow.collectLatest

class SpaceFlightNewsSearchScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val spaceFlightNewsSearchViewModel = getScreenModel<SpaceFlightNewsSearchViewModel>()

        val state by spaceFlightNewsSearchViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            spaceFlightNewsSearchViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is SpaceFlightNewsSearchContract.Effect.NavigateToDetailSpaceFlightNews ->
                        navigator.push(SpaceFlightNewsDetailScreen(effect.idSpaceFlightNews))

                    is SpaceFlightNewsSearchContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                BackNavActionAppBar(
                    title = "Search Spaceflight News",
                    navIcon = {
                        ArrowBackIcon {
                            spaceFlightNewsSearchViewModel.setEvent(
                                SpaceFlightNewsSearchContract.Event.OnBackPressed
                            )
                        }
                    }
                )
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.filteredSpaceFlightNews,
                successView = { news ->
                    SpaceFlightNewsSearchScreenComponent(
                        news = news,
                        spaceFlightNewsSearchViewModel = spaceFlightNewsSearchViewModel,
                    )
                },
                onTryAgain = { spaceFlightNewsSearchViewModel.setEvent(SpaceFlightNewsSearchContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = {
                    spaceFlightNewsSearchViewModel.setEvent(
                        SpaceFlightNewsSearchContract.Event.OnTryCheckAgainClick
                    )
                }
            )
        }
    }
}