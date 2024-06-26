package com.example.rocketnews.presentation.ui.screens.rockets

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.helpers.RocketId
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState


interface RocketsContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnFavoritesClick : Event
        data object OnSearchClick : Event
        data class OnRocketClick(val idRocket: RocketId) : Event
        data object OnBackClick : Event
        data class OnSearchTextChanged(val searchText: String) : Event
    }

    data class State(
        val rockets: ResourceUiState<List<Rocket>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailRocket(val idRocket: RocketId) : Effect
        data object NavigateToFavorites : Effect
        data object ShowSearch : Effect
        data object HideSearch : Effect
    }
}