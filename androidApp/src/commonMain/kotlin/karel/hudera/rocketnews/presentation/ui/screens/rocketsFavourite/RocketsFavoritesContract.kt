package karel.hudera.rocketnews.presentation.ui.screens.rocketsFavourite

import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.UiEffect
import karel.hudera.rocketnews.presentation.mvi.UiEvent
import karel.hudera.rocketnews.presentation.mvi.UiState

interface RocketsFavoritesContract {
    sealed interface Event : UiEvent {
        data object OnBackPressed : Event
        data object OnTryCheckAgainClick : Event
        data class OnRocketClick(val idRocket: String) : Event
    }

    data class State(
        val rocketsFavorites: ResourceUiState<List<Rocket>>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailRocket(val idRocket: String) : Effect
        data object BackNavigation : Effect
    }
}