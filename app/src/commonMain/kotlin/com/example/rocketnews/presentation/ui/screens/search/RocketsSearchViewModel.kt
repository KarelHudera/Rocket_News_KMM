package com.example.rocketnews.presentation.ui.screens.search

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.rocketnews.domain.interactors.GetRocketsUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketsSearchViewModel(
    private val getCharactersUseCase: GetRocketsUseCase,
) : BaseViewModel<RocketsSearchContract.Event, RocketsSearchContract.State, RocketsSearchContract.Effect>() {

    init {
        getCharacters()
    }

    override fun createInitialState(): RocketsSearchContract.State =
        RocketsSearchContract.State(rockets = ResourceUiState.Idle)

    override fun handleEvent(event: RocketsSearchContract.Event) {
        when (event) {
            is RocketsSearchContract.Event.OnTryCheckAgainClick -> getCharacters()
            is RocketsSearchContract.Event.OnRocketClick -> setEffect {
                RocketsSearchContract.Effect.NavigateToDetailCharacter(
                    event.idRocket
                )
            }
            is RocketsSearchContract.Event.OnBackPressed -> setEffect { RocketsSearchContract.Effect.BackNavigation }
        }
    }

    private fun getCharacters() {
        setState { copy(rockets = ResourceUiState.Loading) }
        coroutineScope.launch {
            getCharactersUseCase(Unit)
                .onSuccess {
                    setState {
                        copy(
                            rockets = if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                    co.touchlab.kermit.Logger.i { "$it" }
                }
                .onFailure { setState { copy(rockets = ResourceUiState.Error()) }
                    co.touchlab.kermit.Logger.i { "$it " }
                }
        }
    }
}
