package com.example.rocketnews.presentation.ui.screens.spaceFlightNewsSearch

import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.example.rocketnews.domain.interactors.GetSpaceFlightNewsUseCase
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.NewsOffset
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceFlightNewsSearchViewModel(
    private val getSpaceFlightUseCase: GetSpaceFlightNewsUseCase,
) : BaseViewModel<SpaceFlightNewsSearchContract.Event, SpaceFlightNewsSearchContract.State, SpaceFlightNewsSearchContract.Effect>() {

    var list = emptyList<SpaceFlightNews>()

    private val _newsOffset = MutableStateFlow(0)
    private val newsOffset = _newsOffset.asStateFlow()

    init {
        getSpaceFlightNews(newsOffset.value.toString())
    }

    override fun createInitialState(): SpaceFlightNewsSearchContract.State =
        SpaceFlightNewsSearchContract.State(filteredSpaceFlightNews = ResourceUiState.Idle)

    override fun handleEvent(event: SpaceFlightNewsSearchContract.Event) {
        when (event) {
            is SpaceFlightNewsSearchContract.Event.OnTryCheckAgainClick -> getSpaceFlightNews(
                newsOffset.value.toString()
            )

            is SpaceFlightNewsSearchContract.Event.OnSpaceFlightNewsClick -> setEffect {
                SpaceFlightNewsSearchContract.Effect.NavigateToDetailSpaceFlightNews(
                    event.idSpaceFlightNews
                )
            }

            is SpaceFlightNewsSearchContract.Event.OnBackPressed -> setEffect { SpaceFlightNewsSearchContract.Effect.BackNavigation }
            is SpaceFlightNewsSearchContract.Event.OnSearchTextChanged -> {
                val searchText = event.searchText
                filterNews(searchText)
            }
        }
    }

    private fun getSpaceFlightNews(newsOffset: NewsOffset) {
        setState { copy(filteredSpaceFlightNews = ResourceUiState.Loading) }
        screenModelScope.launch {
            getSpaceFlightUseCase(newsOffset)
                .onSuccess {
                    setState {
                        copy(
                            filteredSpaceFlightNews = if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                    list = it
                    Logger.i { "$it" }
                }
                .onFailure {
                    setState { copy(filteredSpaceFlightNews = ResourceUiState.Error()) }
                    Logger.i { "$it " }
                }
        }
    }

    private fun filterNews(searchText: String) {
        val filteredNews = list.filter { news ->
            news.title.contains(searchText, ignoreCase = true) ||
                    news.summary.contains(searchText, ignoreCase = true)
        }

        setState {
            copy(filteredSpaceFlightNews = ResourceUiState.Success(filteredNews))
        }
    }
}