package com.example.rocketnews.presentation.ui.screens.news

import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.example.rocketnews.domain.interactors.GetNewsUseCase
import com.example.rocketnews.helpers.NewsDate
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
) : BaseViewModel<NewsContract.Event, NewsContract.State, NewsContract.Effect>() {

    private val newsDateFromDatePicker = MutableStateFlow("")

    init {
        getNews(newsDateFromDatePicker.value)
    }

    fun setNewsDateFromDatePicker(time: LocalDate) {
        newsDateFromDatePicker.value = time.toString()
        getNews(newsDateFromDatePicker.value)
    }

    private val _showNewsDatePickerDialog = MutableStateFlow(false)
    val showNewsDatePickerDialog = _showNewsDatePickerDialog.asStateFlow()
    fun setNewsDatePickerDialog(show: Boolean) {
        _showNewsDatePickerDialog.value = show
    }

    private val _showNewsInfoDialog = MutableStateFlow(false)
    val showNewsInfoDialog = _showNewsInfoDialog.asStateFlow()
    fun setNewsInfoBottomSheet(show: Boolean) {
        _showNewsInfoDialog.value = show
    }

    override fun createInitialState(): NewsContract.State =
        NewsContract.State(news = ResourceUiState.Idle)

    override fun handleEvent(event: NewsContract.Event) {
        when (event) {
            NewsContract.Event.OnTryCheckAgainClick -> getNews(newsDateFromDatePicker.value)
            NewsContract.Event.OnInfoBottomSheetClick -> setEffect { NewsContract.Effect.ShowInfoBottomSheet }
            NewsContract.Event.OnDatePickerClick -> setEffect { NewsContract.Effect.ShowDatePicker }
            NewsContract.Event.OnImageClick -> setEffect { NewsContract.Effect.NavigateToNewsImage }
        }
    }

    private fun getNews(newsDate: NewsDate) {
        setState { copy(news = ResourceUiState.Loading) }
        screenModelScope.launch {
            getNewsUseCase(newsDate)
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