package com.example.rocketnews.presentation.ui.screens.spaceFlightNews

import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface SpaceFlightNewsContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data class OnSpaceFlightNewsClick(val idSpaceFlightNews: String) :Event

    }

    data class State(
        val spaceFlightNews: ResourceUiState<List<SpaceFlightNews>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailSpaceFlightNews(val idSpaceFlightNews: String) : Effect
    }
}