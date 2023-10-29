package com.example.rocketnews.presentation.ui.screens.rocketDetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
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
import com.example.rocketnews.presentation.ui.common.ActionBar
import com.example.rocketnews.presentation.ui.common.RocketDetail
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf

class RocketDetailScreen(
    private val rocketId: String,
) : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val rocketDetailViewModel =
            getScreenModel<RocketDetailViewModel> { parametersOf(rocketId) }

        val state by rocketDetailViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            rocketDetailViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is RocketDetailContract.Effect.RocketAdded ->
                        scaffoldState.snackbarHostState.showSnackbar("Rocket added to favorites")

                    is RocketDetailContract.Effect.RocketRemoved ->
                        scaffoldState.snackbarHostState.showSnackbar("Rocket removed from favorites")

                    is RocketDetailContract.Effect.BackNavigation ->
                        navigator.pop()
                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ActionBar(
                    rocket = state.rocket,
                    favorite = state.isFavorite,
                    onActionFavorite = { rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnFavoriteClick) },
                    onBackPressed = { rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnBackPressed) }
                )
            },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.rocket,
                successView = { rocket ->
                    RocketDetail(rocket)
                },
                onTryAgain = { rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}