package com.example.rocketnews.presentation.ui.features.characters_favorites

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.rocketnews.domain.interactors.GetRocketsFavoritesUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketsFavoritesViewModel(
    private val getCharactersFavoritesUseCase: GetRocketsFavoritesUseCase
) : BaseViewModel<CharactersFavoritesContract.Event, CharactersFavoritesContract.State, CharactersFavoritesContract.Effect>() {

    init {
        getCharactersFavorites()
    }

    override fun createInitialState(): CharactersFavoritesContract.State =
        CharactersFavoritesContract.State(
            charactersFavorites = ResourceUiState.Idle
        )

    override fun handleEvent(event: CharactersFavoritesContract.Event) {
        when (event) {
            CharactersFavoritesContract.Event.OnTryCheckAgainClick -> getCharactersFavorites()
            is CharactersFavoritesContract.Event.OnCharacterClick ->
                setEffect { CharactersFavoritesContract.Effect.NavigateToDetailCharacter(event.idRocket) }

            CharactersFavoritesContract.Event.OnBackPressed ->
                setEffect { CharactersFavoritesContract.Effect.BackNavigation }
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