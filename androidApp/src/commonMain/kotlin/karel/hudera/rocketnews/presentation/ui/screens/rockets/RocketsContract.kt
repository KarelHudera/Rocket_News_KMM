package karel.hudera.rocketnews.presentation.ui.screens.rockets

import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.helpers.RocketId
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.UiEffect
import karel.hudera.rocketnews.presentation.mvi.UiEvent
import karel.hudera.rocketnews.presentation.mvi.UiState


interface RocketsContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnFavoritesClick : Event
        data object OnSearchClick : Event
        data class OnRocketClick(val idRocket: RocketId) : Event
        data object OnBackClick : Event
        data class OnSearchTextChanged(val searchText: String) : Event
    }

    data class State(
        val rockets: ResourceUiState<List<Rocket>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailRocket(val idRocket: RocketId) : Effect
        data object NavigateToFavorites : Effect
        data object ShowSearch : Effect
        data object HideSearch : Effect
    }
}