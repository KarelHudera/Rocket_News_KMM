package com.example.rocketnews.presentation.ui.screens.news

import cafe.adriel.voyager.core.model.coroutineScope
import co.touchlab.kermit.Logger
import com.example.rocketnews.domain.interactors.GetNewsUseCase
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
) : BaseViewModel<NewsContract.Event, NewsContract.State, NewsContract.Effect>() {

    init {
        getNews()
    }

    override fun createInitialState(): NewsContract.State =
        NewsContract.State(news = ResourceUiState.Idle)

    override fun handleEvent(event: NewsContract.Event) {
        when (event) {
            NewsContract.Event.OnTryCheckAgainClick -> getNews()
            NewsContract.Event.OnRocketButtonClick -> setEffect { NewsContract.Effect.NavigateToRockets }
        }
    }

    private fun getNews() {
        setState { copy(news = ResourceUiState.Loading) }
        coroutineScope.launch {
            getNewsUseCase(Unit)
                .onSuccess {
                    setState {
                        copy(
                            news = if (it.explanation.isEmpty()) // TODO: hack
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                    Logger.i { "$it" }
                }
                .onFailure {
                    setState { copy(news = ResourceUiState.Error()) }
                    Logger.i { "$it " }
                }
        }
    }
}
