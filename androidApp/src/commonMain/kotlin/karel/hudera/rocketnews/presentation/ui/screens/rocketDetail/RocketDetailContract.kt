package karel.hudera.rocketnews.presentation.ui.screens.rocketDetail

import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.UiEffect
import karel.hudera.rocketnews.presentation.mvi.UiEvent
import karel.hudera.rocketnews.presentation.mvi.UiState

interface RocketDetailContract {
    sealed interface Event : UiEvent {
        data object OnFavoriteClick : Event
        data object OnTryCheckAgainClick : Event
        data object OnEmptyUrlClick : Event
        data object OnBackPressed : Event
    }

    data class State(
        val rocket: ResourceUiState<Rocket>,
        val isFavorite: ResourceUiState<Boolean>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data object RocketAdded : Effect
        data object RocketRemoved : Effect
        data object EmptyUrl : Effect
        data object BackNavigation : Effect
    }
}