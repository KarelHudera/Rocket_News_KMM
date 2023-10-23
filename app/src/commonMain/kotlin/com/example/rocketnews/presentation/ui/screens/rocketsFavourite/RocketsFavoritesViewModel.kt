package com.example.rocketnews.presentation.ui.screens.rocketsFavourite

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.rocketnews.domain.interactors.GetRocketsFavoritesUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketsFavoritesViewModel(
    private val getCharactersFavoritesUseCase: GetRocketsFavoritesUseCase
) : BaseViewModel<RocketsFavoritesContract.Event, RocketsFavoritesContract.State, RocketsFavoritesContract.Effect>() {

    init {
        getCharactersFavorites()
    }

    override fun createInitialState(): RocketsFavoritesContract.State =
        RocketsFavoritesContract.State(
            charactersFavorites = ResourceUiState.Idle
        )

    override fun handleEvent(event: RocketsFavoritesContract.Event) {
        when (event) {
            RocketsFavoritesContract.Event.OnTryCheckAgainClick -> getCharactersFavorites()
            is RocketsFavoritesContract.Event.OnCharacterClick ->
                setEffect { RocketsFavoritesContract.Effect.NavigateToDetailCharacter(event.idRocket) }

            RocketsFavoritesContract.Event.OnBackPressed ->
                setEffect { RocketsFavoritesContract.Effect.BackNavigation }
        }
    }

    private fun getCharactersFavorites() {
        setState { copy(charactersFavorites = ResourceUiState.Loading) }
        coroutineScope.launch {
            getCharactersFavoritesUseCase(Unit).collect {
                it.onSuccess {
                    setState {
                        copy(
                            charactersFavorites =
                            if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                }.onFailure { setState { copy(charactersFavorites = ResourceUiState.Error()) } }
            }
        }
    }
}