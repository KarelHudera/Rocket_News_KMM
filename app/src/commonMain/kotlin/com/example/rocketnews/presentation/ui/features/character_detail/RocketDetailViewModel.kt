package com.example.rocketnews.presentation.ui.features.character_detail

import cafe.adriel.voyager.core.model.coroutineScope
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
    private val characterId: Long,
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
            RocketDetailContract.Event.OnFavoriteClick -> switchCharacterFavorite(characterId)
            RocketDetailContract.Event.OnTryCheckAgainClick -> getCharacter(characterId)
            RocketDetailContract.Event.OnBackPressed -> setEffect { RocketDetailContract.Effect.BackNavigation }
        }
    }

    private fun getCharacter(rocketId: Long) {
        setState { copy(rocket = ResourceUiState.Loading) }
        coroutineScope.launch {
            getCharacterUseCase(rocketId)
                .onSuccess { setState { copy(rocket = ResourceUiState.Success(it)) } }
                .onFailure { setState { copy(rocket = ResourceUiState.Error()) } }
        }
    }

    private fun checkIfIsFavorite(idRocket: Long) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        coroutineScope.launch {
            isCharacterFavoriteUseCase(idRocket)
                .onSuccess { setState { copy(isFavorite = ResourceUiState.Success(it)) } }
                .onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }

    private fun switchCharacterFavorite(idRocket: Long) {
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