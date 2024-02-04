package com.example.rocketnews.presentation.ui.screens.rocketsSearch

import cafe.adriel.voyager.core.model.screenModelScope
import com.example.rocketnews.domain.interactors.GetRocketsUseCase
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketsSearchViewModel(
    private val getRocketsUseCase: GetRocketsUseCase,
) : BaseViewModel<RocketsSearchContract.Event, RocketsSearchContract.State, RocketsSearchContract.Effect>() {

    var list = emptyList<Rocket>()

    init {
        getRockets()
    }

    override fun createInitialState(): RocketsSearchContract.State =
        RocketsSearchContract.State(filteredRockets = ResourceUiState.Idle)

    override fun handleEvent(event: RocketsSearchContract.Event) {
        when (event) {
            is RocketsSearchContract.Event.OnTryCheckAgainClick -> getRockets()
            is RocketsSearchContract.Event.OnRocketClick -> setEffect {
                RocketsSearchContract.Effect.NavigateToDetailRocket(
                    event.idRocket
                )
            }

            is RocketsSearchContract.Event.OnBackPressed -> setEffect { RocketsSearchContract.Effect.BackNavigation }
            is RocketsSearchContract.Event.OnSearchTextChanged -> {
                val searchText = event.searchText
                filterRockets(searchText)
            }
        }
    }

    private fun getRockets() {
        setState { copy(filteredRockets = ResourceUiState.Loading) }
        screenModelScope.launch {
            getRocketsUseCase(Unit)
                .onSuccess {
                    setState {
                        copy(
                            filteredRockets = if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                    list = it
                }
                .onFailure {
                    setState { copy(filteredRockets = ResourceUiState.Error()) }
                }
        }
    }

    private fun filterRockets(searchText: String) {
        val filteredRockets = list.filter { rocket ->
            rocket.name.contains(searchText, ignoreCase = true)
        }

        setState {
            copy(filteredRockets = ResourceUiState.Success(filteredRockets))
        }
    }

}
