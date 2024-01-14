package com.example.rocketnews.presentation.ui.screens.rocketsFavourite

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

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