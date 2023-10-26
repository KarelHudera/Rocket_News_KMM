package com.example.rocketnews.presentation.ui.screens.rocketDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.seiko.imageloader.rememberAsyncImagePainter
import com.example.rocketnews.presentation.ui.common.ActionBarIcon
import com.example.rocketnews.presentation.ui.common.ArrowBackIcon
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf

class CharacterDetailScreen(
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
                    is RocketDetailContract.Effect.CharacterAdded ->
                        scaffoldState.snackbarHostState.showSnackbar("Character added to favorites")

                    is RocketDetailContract.Effect.CharacterRemoved ->
                        scaffoldState.snackbarHostState.showSnackbar("Character removed from favorites")

                    is RocketDetailContract.Effect.BackNavigation ->
                        navigator.pop()
                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ActionBar(
                    character = state.rocket,
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
                    CharacterDetail(rocket)
                },
                onTryAgain = { rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

@Composable
fun CharacterDetail(rocket: Rocket) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = rocket.name,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.size(10.dp))
        Image(
            modifier = Modifier.size(200.dp),
            painter = rememberAsyncImagePainter(rocket.patchLarge),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "${rocket.date_utc}, ${rocket.name}",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(10.dp))
    }
}

@Composable
fun ActionBar(
    character: ResourceUiState<Rocket>,
    favorite: ResourceUiState<Boolean>,
    onActionFavorite: () -> Unit,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            ManagementResourceUiState(
                resourceUiState = character,
                successView = { Text(text = it.name) },
                loadingView = { Text(text = "....") },
                onCheckAgain = {},
                onTryAgain = {}
            )
        },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        actions = {
            ManagementResourceUiState(
                resourceUiState = favorite,
                successView = {
                    ActionBarIcon(
                        onClick = onActionFavorite,
                        icon = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                    )
                },
                loadingView = {
                    ActionBarIcon(
                        enabled = false,
                        onClick = {},
                        icon = Icons.Filled.Favorite
                    )
                },
                onCheckAgain = {},
                onTryAgain = {}
            )
        }
    )
}