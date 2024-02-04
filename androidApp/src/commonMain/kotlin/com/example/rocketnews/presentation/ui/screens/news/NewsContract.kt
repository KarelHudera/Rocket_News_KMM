package com.example.rocketnews.presentation.ui.screens.news

import com.example.rocketnews.domain.model.News
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

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
