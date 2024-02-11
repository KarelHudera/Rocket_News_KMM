package com.example.rocketnews.presentation.ui.screens.spaceFlightNews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.presentation.theme.spacing
import com.example.rocketnews.presentation.ui.common.LoadingComponent
import com.example.rocketnews.presentation.ui.common.screenComponents.SpaceFlightNewsScreenComponent
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.common.topBars.SpaceFlightNewsActionAppBar
import com.example.rocketnews.presentation.ui.screens.spaceFlightNewsDetail.SpaceFlightNewsDetailScreen
import kotlinx.coroutines.flow.collectLatest

class SpaceFlightNewsScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val spaceFlightNewsViewModel = getScreenModel<SpaceFlightNewsViewModel>()

        val state by spaceFlightNewsViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            spaceFlightNewsViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is SpaceFlightNewsContract.Effect.NavigateToDetailSpaceFlightNews ->
                        navigator.push(SpaceFlightNewsDetailScreen(effect.idSpaceFlightNews))

                    is SpaceFlightNewsContract.Effect.ShowSearch ->
                        spaceFlightNewsViewModel.setSearchBarVisibility(true)

                    is SpaceFlightNewsContract.Effect.HideSearch ->
                        spaceFlightNewsViewModel.setSearchBarVisibility(false)
                }
            }
        }

        Scaffold(
            topBar = {
                SpaceFlightNewsActionAppBar(
                    title = "Daily News From Spaceflight",
                    spaceFlightNewsViewModel = spaceFlightNewsViewModel,
                    isShadowEnabled = true
                )
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.spaceFlightNews,
                successView = { spaceflightNews ->
                    SpaceFlightNewsScreenComponent(
                        spaceflightNews = spaceflightNews,
                        spaceFlightNewsViewModel = spaceFlightNewsViewModel
                    )
                },
                loadingView = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MaterialTheme.spacing.horizontal)
                            .verticalScroll(rememberScrollState())
                    ) {
                        repeat(3) {
                            LoadingComponent(
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth()
                                    .height(290.dp),
                                cornerShape = 8.dp
                            )
                        }
                    }
                },
                onTryAgain = { spaceFlightNewsViewModel.setEvent(SpaceFlightNewsContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { spaceFlightNewsViewModel.setEvent(SpaceFlightNewsContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}