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
    private val getCharacterUseCase: GetRocketUseCase,
    private val isCharacterFavoriteUseCase: IsRocketFavoriteUseCase,
    private val switchCharacterFavoriteUseCase: SwitchRocketFavoriteUseCase,
    private val characterId: String,
) : BaseViewModel<RocketDetailContract.Event, RocketDetailContract.State, RocketDetailContract.Effect>() {

    init {
        getCharacter(characterId)
        checkIfIsFavorite(characterId)
    }

    override fun createInitialState(): RocketDetailContract.State =
        RocketDetailContract.State(
            rocket = ResourceUiState.Idle,
            isFavorite = ResourceUiState.Idle,
        )

    override fun handleEvent(event: RocketDetailContract.Event) {
        when (event) {
            is RocketDetailContract.Event.OnFavoriteClick -> switchCharacterFavorite(characterId)
            is RocketDetailContract.Event.OnTryCheckAgainClick -> getCharacter(characterId)
            is RocketDetailContract.Event.OnBackPressed -> setEffect { RocketDetailContract.Effect.BackNavigation }
        }
    }

    private fun getCharacter(rocketId: String) {
        setState { copy(rocket = ResourceUiState.Loading) }
        coroutineScope.launch {
            getCharacterUseCase(rocketId)
                .onSuccess {
                    setState { copy(rocket = ResourceUiState.Success(it)) }
                    Logger.i { "$it SUCCESS" }
                }
                .onFailure { setState { copy(rocket = ResourceUiState.Error()) }
                    Logger.i { "$it FAILURE" }
                }
        }
    }

    private fun checkIfIsFavorite(idRocket: String) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        coroutineScope.launch {
            isCharacterFavoriteUseCase(idRocket)
                .onSuccess { setState { copy(isFavorite = ResourceUiState.Success(it)) } }
                .onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }

    private fun switchCharacterFavorite(idRocket: String) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        coroutineScope.launch {
            switchCharacterFavoriteUseCase(idRocket)
                .onSuccess {
                    setState { copy(isFavorite = ResourceUiState.Success(it)) }
                    setEffect { RocketDetailContract.Effect.CharacterAdded }
                }.onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }
}