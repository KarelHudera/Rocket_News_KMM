package com.example.rocketnews.presentation.ui.screens.rockets

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.example.rocketnews.domain.interactors.GetRocketsUseCase
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RocketsViewModel(
    private val getRocketsUseCase: GetRocketsUseCase,
) : BaseViewModel<RocketsContract.Event, RocketsContract.State, RocketsContract.Effect>() {

    var list = emptyList<Rocket>()
    var searchText = MutableStateFlow(mutableStateOf(TextFieldValue("")))

    init {
        getRockets()
    }

    private val _showSearchBar = MutableStateFlow(false)
    val showSearchBar = _showSearchBar.asStateFlow()

    fun setSearchBarVisibility(show: Boolean) {
        _showSearchBar.value = show
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
            is RocketsContract.Event.OnSearchClick -> setEffect { RocketsContract.Effect.ShowSearch }
            is RocketsContract.Event.OnBackClick -> setEffect { RocketsContract.Effect.HideSearch }
            is RocketsContract.Event.OnSearchTextChanged -> {
                searchText.value.value = TextFieldValue(event.searchText)
                filterRockets(searchText.value.value.text)
            }
        }
    }

    private fun getRockets() {
        setState { copy(rockets = ResourceUiState.Loading) }
        screenModelScope.launch {
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
                    list = it
                    Logger.i { "$it" }
                }
                .onFailure {
                    setState { copy(rockets = ResourceUiState.Error()) }
                    Logger.i { "$it " }
                }
        }
    }

    private fun filterRockets(searchText: String) {
        val filteredRockets = list.filter { rocket ->
            rocket.name.contains(searchText, ignoreCase = true)
        }

        setState {
            copy(rockets = ResourceUiState.Success(filteredRockets))
        }
    }
}
