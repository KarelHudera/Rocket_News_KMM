package com.example.rocketnews.presentation.ui.screens.rocketsSearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.rocketnews.presentation.ui.common.RocketsList
import com.example.rocketnews.presentation.ui.common.Space
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

                    is RocketsSearchContract.Effect.NavigateToDetailRocket ->
                        navigator.push(RocketDetailScreen(effect.idRocket))
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
                    Column {
                        OutlinedTextField(
                            value = text,
                            onValueChange = {
                                text = it
                                rocketsSearchViewModel.setEvent(
                                    RocketsSearchContract.Event.OnSearchTextChanged(it)
                                )
                            },
                            modifier = Modifier
                                .padding(top = 16.dp, end = 16.dp, start = 16.dp)
                                .fillMaxWidth(),
                            placeholder = { Text(text = "Search rocket") },
                        )
                        if (rockets.isEmpty()) {
                            SearchErrorMessage(text)
                        } else {
                            RocketsList(
                                rockets = rockets,
                                onRocketClick = { idRocket ->
                                    rocketsSearchViewModel.setEvent(
                                        RocketsSearchContract.Event.OnRocketClick(idRocket)
                                    )
                                }
                            )
                        }
                    }
                },
                onTryAgain = { rocketsSearchViewModel.setEvent(RocketsSearchContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketsSearchViewModel.setEvent(RocketsSearchContract.Event.OnTryCheckAgainClick) },
            )
        }
    }

    @Composable
    private fun SearchErrorMessage(text: String) {
        Box(Modifier.fillMaxSize(), Alignment.Center) {
            Column {
                Text(
                    text = "Couldn't find \"$text\"",
                    style = MaterialTheme.typography.h5
                )
                Space()
                Text(
                    text = "Try something else",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}