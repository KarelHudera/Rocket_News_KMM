package com.example.rocketnews.presentation.ui.screens.rockets

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.rocketnews.domain.interactors.GetRocketsUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketsViewModel(
    private val getRocketsUseCase: GetRocketsUseCase,
) : BaseViewModel<RocketsContract.Event, RocketsContract.State, RocketsContract.Effect>() {

    init {
        getRockets()
    }

    override fun createInitialState(): RocketsContract.State =
        RocketsContract.State(rockets = ResourceUiState.Idle)

    override fun handleEvent(event: RocketsContract.Event) {
        when (event) {
            is RocketsContract.Event.OnTryCheckAgainClick -> getRockets()
            is RocketsContract.Event.OnRocketClick -> setEffect {
                RocketsContract.Effect.NavigateToDetailRocket(
                    event.idRocket
                )
            }
            is RocketsContract.Event.OnFavoritesClick -> setEffect { RocketsContract.Effect.NavigateToFavorites }
            is RocketsContract.Event.OnBackPressed -> setEffect { RocketsContract.Effect.BackNavigation }
            is RocketsContract.Event.OnSearchClick -> setEffect { RocketsContract.Effect.NavigateToSearch }
        }
    }

    private fun getRockets() {
        setState { copy(rockets = ResourceUiState.Loading) }
        coroutineScope.launch {
            getRocketsUseCase(Unit)
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
