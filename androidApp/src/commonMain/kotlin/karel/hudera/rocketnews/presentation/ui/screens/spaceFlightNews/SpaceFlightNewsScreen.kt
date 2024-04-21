package karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews

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
import karel.hudera.rocketnews.presentation.ui.common.screenComponents.SpaceFlightNewsScreenComponent
import karel.hudera.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import karel.hudera.rocketnews.presentation.ui.common.topBars.SpaceFlightNewsActionAppBar
import karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNewsDetail.SpaceFlightNewsDetailScreen
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
                onTryAgain = { spaceFlightNewsViewModel.setEvent(SpaceFlightNewsContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { spaceFlightNewsViewModel.setEvent(SpaceFlightNewsContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}