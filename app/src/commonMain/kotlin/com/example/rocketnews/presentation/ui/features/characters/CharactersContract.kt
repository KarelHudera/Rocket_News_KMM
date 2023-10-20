package com.example.rocketnews.presentation.ui.features.characters

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface CharactersContract {
    sealed interface Event : UiEvent {
        object OnTryCheckAgainClick : Event
        object OnFavoritesClick : Event
        data class OnCharacterClick(val idRocket: String) : Event
    }

    data class State(
        val characters: ResourceUiState<List<Rocket>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCharacter(val idRocket: String) : Effect
        object NavigateToFavorites : Effect
    }
}


