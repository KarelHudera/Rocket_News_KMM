package com.example.rocketnews.presentation.ui.screens.newsImage

import com.example.rocketnews.presentation.mvi.UiEffect
import com.example.rocketnews.presentation.mvi.UiEvent
import com.example.rocketnews.presentation.mvi.UiState

interface NewsImageContract {
    sealed interface Event : UiEvent {
        data object OnBackPressed : Event
    }

    data class State(
        val state: Unit
    ) : UiState

    sealed interface Effect : UiEffect {
        data object BackNavigation : Effect
    }
}