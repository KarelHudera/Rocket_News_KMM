package com.example.rocketnews.presentation.ui.features.characters_favorites

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface CharactersFavoritesContract {
    sealed interface Event : UiEvent {
        object OnBackPressed : Event
        object OnTryCheckAgainClick : Event
        data class OnCharacterClick(val idRocket: String) : Event
    }

    data class State(
        val charactersFavorites: ResourceUiState<List<Rocket>>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCharacter(val idRocket: String) : Effect
        object BackNavigation : Effect

    }
}