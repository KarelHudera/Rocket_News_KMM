package com.example.rocketnews.presentation.ui.screens.rocketDetail

import cafe.adriel.voyager.core.model.coroutineScope
import co.touchlab.kermit.Logger
import com.example.rocketnews.domain.interactors.GetRocketUseCase
import com.example.rocketnews.domain.interactors.IsRocketFavoriteUseCase
import com.example.rocketnews.domain.interactors.SwitchRocketFavoriteUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketDetailViewModel(
    private val getRocketUseCase: GetRocketUseCase,
    private val isRocketFavoriteUseCase: IsRocketFavoriteUseCase,
    private val switchRocketFavoriteUseCase: SwitchRocketFavoriteUseCase,
    private val rocketId: String,
) : BaseViewModel<RocketDetailContract.Event, RocketDetailContract.State, RocketDetailContract.Effect>() {

    init {
        getCharacter(rocketId)
        checkIfIsFavorite(rocketId)
    }

    override fun createInitialState(): RocketDetailContract.State =
        RocketDetailContract.State(
            rocket = ResourceUiState.Idle,
            isFavorite = ResourceUiState.Idle,
        )

    override fun handleEvent(event: RocketDetailContract.Event) {
        when (event) {
            is RocketDetailContract.Event.OnFavoriteClick -> switchCharacterFavorite(rocketId)
            is RocketDetailContract.Event.OnTryCheckAgainClick -> getCharacter(rocketId)
            is RocketDetailContract.Event.OnBackPressed -> setEffect { RocketDetailContract.Effect.BackNavigation }
        }
    }

    private fun getCharacter(rocketId: String) {
        setState { copy(rocket = ResourceUiState.Loading) }
        coroutineScope.launch {
            getRocketUseCase(rocketId)
                .onSuccess {
                    setState { copy(rocket = ResourceUiState.Success(it)) }
                    Logger.i { "$it SUCCESS" }
                }
                .onFailure {
                    setState { copy(rocket = ResourceUiState.Error()) }
                    Logger.i { "$it FAILURE" }
                }
        }
    }

    private fun checkIfIsFavorite(idRocket: String) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        coroutineScope.launch {
            isRocketFavoriteUseCase(idRocket)
                .onSuccess { setState { copy(isFavorite = ResourceUiState.Success(it)) } }
                .onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }

    private fun switchCharacterFavorite(idRocket: String) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        coroutineScope.launch {
            switchRocketFavoriteUseCase(idRocket)
                .onSuccess {
                    setState { copy(isFavorite = ResourceUiState.Success(it)) }
                    if (it) {
                        setEffect { RocketDetailContract.Effect.RocketAdded }
                    } else {
                        setEffect { RocketDetailContract.Effect.RocketRemoved }
                    }

                }.onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }

}