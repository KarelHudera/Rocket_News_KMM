package com.example.rocketnews.presentation.ui.screens.spaceFlightNewsDetail

import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

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