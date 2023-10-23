package com.example.rocketnews.presentation.ui.screens.rocketDetail

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface RocketDetailContract {
    sealed interface Event : UiEvent {
        data object OnFavoriteClick : Event
        data object OnTryCheckAgainClick : Event
        data object OnBackPressed : Event
    }

    data class State(
        val rocket: ResourceUiState<Rocket>,
        val isFavorite: ResourceUiState<Boolean>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data object CharacterAdded : Effect
        data object CharacterRemoved : Effect
        data object BackNavigation : Effect
    }
}