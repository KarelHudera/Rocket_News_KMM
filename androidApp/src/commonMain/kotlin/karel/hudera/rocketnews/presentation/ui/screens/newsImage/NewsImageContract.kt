package karel.hudera.rocketnews.presentation.ui.screens.newsImage

import karel.hudera.rocketnews.presentation.mvi.UiEffect
import karel.hudera.rocketnews.presentation.mvi.UiEvent
import karel.hudera.rocketnews.presentation.mvi.UiState

interface NewsImageContract {
    sealed interface Event : UiEvent {
        data object OnBackPressed : Event
    }

    data class State(
        val state: Unit
    ) : UiState

    sealed interface Effect : UiEffect {
        data object BackNavigation : Effect
    }
}