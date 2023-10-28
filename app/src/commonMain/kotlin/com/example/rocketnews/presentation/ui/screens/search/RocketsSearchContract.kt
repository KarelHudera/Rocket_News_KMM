package com.example.rocketnews.presentation.ui.screens.search

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface RocketsSearchContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnBackPressed : Event
        data class OnRocketClick(val idRocket: String) : Event
        data class OnSearchTextChanged(val searchText: String) : Event
    }

    data class State(
        val rockets: ResourceUiState<List<Rocket>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCharacter(val idRocket: String) : Effect
        data object BackNavigation : Effect

    }
}
