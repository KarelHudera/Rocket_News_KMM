package com.example.rocketnews.presentation.ui.screens.spaceFlightNewsSearch

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface SpaceFlightNewsSearchContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnBackPressed :Event
        data class OnSpaceFlightNewsClick(val idSpaceFlightNews: String) : Event
        data class OnSearchTextChanged(val searchText: String) : Event
    }

    data class State(
        val filteredSpaceFlightNews: ResourceUiState<List<SpaceFlightNews>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailSpaceFlightNews(val idSpaceFlightNews: String) : Effect
        data object BackNavigation : Effect
    }
}