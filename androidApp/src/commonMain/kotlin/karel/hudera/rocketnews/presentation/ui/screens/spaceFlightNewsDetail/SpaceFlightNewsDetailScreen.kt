package karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNewsDetail

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
import karel.hudera.rocketnews.presentation.ui.common.screenComponents.SpaceFlightNewsDetailScreenComponent
import karel.hudera.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import karel.hudera.rocketnews.presentation.ui.common.topBars.SpaceFlightNewsActionBar
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf


class SpaceFlightNewsDetailScreen(
    private val spaceFlightNewsId: String
) : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val spaceFlightNewsDetailViewModel =
            getScreenModel<SpaceFlightNewsDetailViewModel> { parametersOf(spaceFlightNewsId) }

        val state by spaceFlightNewsDetailViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            spaceFlightNewsDetailViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is SpaceFlightNewsDetailContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                SpaceFlightNewsActionBar(
                    spaceFlightNews = state.spaceFlightNew,
                    onBackPressed = {
                        spaceFlightNewsDetailViewModel.setEvent(
                            SpaceFlightNewsDetailContract.Event.OnBackPressed
                        )
                    }
                )
            },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.spaceFlightNew,
                successView = { spaceFlightNew ->
                    SpaceFlightNewsDetailScreenComponent(spaceFlightNew)
                },
                onTryAgain = { spaceFlightNewsDetailViewModel.setEvent(SpaceFlightNewsDetailContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = {
                    spaceFlightNewsDetailViewModel.setEvent(
                        SpaceFlightNewsDetailContract.Event.OnTryCheckAgainClick
                    )
                }
            )
        }
    }
}