package karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNewsDetail

import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.UiEffect
import karel.hudera.rocketnews.presentation.mvi.UiEvent
import karel.hudera.rocketnews.presentation.mvi.UiState

interface SpaceFlightNewsDetailContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnBackPressed : Event
    }

    data class State(
        val spaceFlightNew: ResourceUiState<SpaceFlightNews>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data object BackNavigation : Effect
    }
}