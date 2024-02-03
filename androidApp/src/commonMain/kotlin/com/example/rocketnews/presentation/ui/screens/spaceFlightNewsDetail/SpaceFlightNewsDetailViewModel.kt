package com.example.rocketnews.presentation.ui.screens.spaceFlightNewsDetail

import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.example.rocketnews.domain.interactors.GetSpaceFlightNewUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class SpaceFlightNewsDetailViewModel(
    private val getSpaceFlightNewUseCase: GetSpaceFlightNewUseCase,
    private val spaceFlightNewsId: String
) : BaseViewModel<SpaceFlightNewsDetailContract.Event, SpaceFlightNewsDetailContract.State, SpaceFlightNewsDetailContract.Effect>() {

    init {
        getSpaceFlightNew(spaceFlightNewsId)
    }

    override fun createInitialState(): SpaceFlightNewsDetailContract.State =
        SpaceFlightNewsDetailContract.State(
            spaceFlightNew = ResourceUiState.Idle,
        )

    override fun handleEvent(event: SpaceFlightNewsDetailContract.Event) {
        when (event) {
            is SpaceFlightNewsDetailContract.Event.OnTryCheckAgainClick -> getSpaceFlightNew(spaceFlightNewsId)
            is SpaceFlightNewsDetailContract.Event.OnBackPressed -> setEffect { SpaceFlightNewsDetailContract.Effect.BackNavigation }
        }
    }

    private fun getSpaceFlightNew(spaceFlightNewsId: String) {
        setState { copy(spaceFlightNew = ResourceUiState.Loading) }
        screenModelScope.launch {
            getSpaceFlightNewUseCase(spaceFlightNewsId)
                .onSuccess {
                    setState { copy(spaceFlightNew = ResourceUiState.Success(it)) }
                    Logger.i { "$it SUCCESS" }
                }
                .onFailure {
                    setState { copy(spaceFlightNew = ResourceUiState.Error()) }
                    Logger.i { "$it FAILURE" }
                }
        }
    }
}