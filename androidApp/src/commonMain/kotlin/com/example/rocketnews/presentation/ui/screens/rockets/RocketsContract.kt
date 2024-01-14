package com.example.rocketnews.presentation.ui.screens.rockets

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface RocketsContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnFavoritesClick : Event
        data object OnBackPressed : Event
        data object OnSearchClick : Event
        data class OnRocketClick(val idRocket: String) : Event
    }

    data class State(
        val rockets: ResourceUiState<List<Rocket>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailRocket(val idRocket: String) : Effect
        data object NavigateToFavorites : Effect
        data object NavigateToSearch : Effect
        data object BackNavigation : Effect

    }
}


