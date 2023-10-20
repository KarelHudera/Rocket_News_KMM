package com.example.rocketnews.presentation.ui.features.characters

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.rocketnews.domain.interactors.GetRocketsUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import io.ktor.util.logging.Logger
import kotlinx.coroutines.launch

class RocketsViewModel(
    private val getCharactersUseCase: GetRocketsUseCase,
) : BaseViewModel<CharactersContract.Event, CharactersContract.State, CharactersContract.Effect>() {

    init {
        getCharacters()
    }

    override fun createInitialState(): CharactersContract.State =
        CharactersContract.State(characters = ResourceUiState.Idle)

    override fun handleEvent(event: CharactersContract.Event) {
        when (event) {
            CharactersContract.Event.OnTryCheckAgainClick -> getCharacters()
            is CharactersContract.Event.OnCharacterClick -> setEffect {
                CharactersContract.Effect.NavigateToDetailCharacter(
                    event.idRocket
                )
            }

            CharactersContract.Event.OnFavoritesClick -> setEffect { CharactersContract.Effect.NavigateToFavorites }
        }
    }

    private fun getCharacters() {
        setState { copy(characters = ResourceUiState.Loading) }
        coroutineScope.launch {
            getCharactersUseCase(Unit)
                .onSuccess {
                    setState {
                        copy(
                            characters = if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                    co.touchlab.kermit.Logger.i { "$it SUCCESS" }

                }
                .onFailure { setState { copy(characters = ResourceUiState.Error()) }
                    co.touchlab.kermit.Logger.i { "$it FAIL" }
                }
        }
    }
}
