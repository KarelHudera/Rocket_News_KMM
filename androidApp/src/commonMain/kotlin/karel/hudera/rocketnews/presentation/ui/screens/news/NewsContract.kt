package karel.hudera.rocketnews.presentation.ui.screens.news

import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.UiEffect
import karel.hudera.rocketnews.presentation.mvi.UiEvent
import karel.hudera.rocketnews.presentation.mvi.UiState

interface NewsContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnInfoBottomSheetClick : Event
        data object OnDatePickerClick : Event
        data object OnImageClick : Event
    }

    data class State(
        val news: ResourceUiState<News>
    ) : UiState

    sealed interface Effect : UiEffect {
        data object ShowInfoBottomSheet : Effect
        data object ShowDatePicker : Effect
        data object NavigateToNewsImage : Effect
    }
}
