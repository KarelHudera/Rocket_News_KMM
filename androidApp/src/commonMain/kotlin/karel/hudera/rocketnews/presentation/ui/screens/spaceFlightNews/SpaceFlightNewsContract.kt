package karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews

import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.UiEffect
import karel.hudera.rocketnews.presentation.mvi.UiEvent
import karel.hudera.rocketnews.presentation.mvi.UiState

interface SpaceFlightNewsContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data class OnSpaceFlightNewsClick(val idSpaceFlightNews: String) :Event
        data object OnSearchClick : Event
        data object OnBackClick : Event
        data class OnSearchTextChanged(val searchText: String) : Event
    }

    data class State(
        val spaceFlightNews: ResourceUiState<List<SpaceFlightNews>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailSpaceFlightNews(val idSpaceFlightNews: String) : Effect
        data object ShowSearch : Effect
        data object HideSearch : Effect
    }
}