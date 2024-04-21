package karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import karel.hudera.rocketnews.domain.interactors.GetSpaceFlightNewsUseCase
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.helpers.NewsOffset
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceFlightNewsViewModel(
    private val getSpaceFlightUseCase: GetSpaceFlightNewsUseCase,
) : BaseViewModel<SpaceFlightNewsContract.Event, SpaceFlightNewsContract.State, SpaceFlightNewsContract.Effect>() {

    var list = emptyList<SpaceFlightNews>()
    var searchText = MutableStateFlow(mutableStateOf(TextFieldValue("")))

    private val _newsOffset = MutableStateFlow(0)
    private val newsOffset = _newsOffset.asStateFlow()

    init {
        getSpaceFlightNews(newsOffset.value.toString())
    }

    private val _showSearchBar = MutableStateFlow(false)
    val showSearchBar = _showSearchBar.asStateFlow()

    fun setSearchBarVisibility(show: Boolean) {
        _showSearchBar.value = show
    }

    fun loadMoreSpaceFLightNews() {
        newsOffset.value + 20
        getSpaceFlightNews(newsOffset.value.toString())
    }

    override fun createInitialState(): SpaceFlightNewsContract.State =
        SpaceFlightNewsContract.State(spaceFlightNews = ResourceUiState.Idle)

    override fun handleEvent(event: SpaceFlightNewsContract.Event) {
        when (event) {
            is SpaceFlightNewsContract.Event.OnTryCheckAgainClick -> getSpaceFlightNews(newsOffset.value.toString())
            is SpaceFlightNewsContract.Event.OnSpaceFlightNewsClick -> setEffect {
                SpaceFlightNewsContract.Effect.NavigateToDetailSpaceFlightNews(
                    event.idSpaceFlightNews
                )
            }
            is SpaceFlightNewsContract.Event.OnSearchClick -> setEffect { SpaceFlightNewsContract.Effect.ShowSearch }
            is SpaceFlightNewsContract.Event.OnBackClick -> setEffect { SpaceFlightNewsContract.Effect.HideSearch }
            is SpaceFlightNewsContract.Event.OnSearchTextChanged -> {
                searchText.value.value = TextFieldValue(event.searchText)
                filterNews(searchText.value.value.text)
            }
        }
    }

    private fun getSpaceFlightNews(newsOffset: NewsOffset) {
        setState { copy(spaceFlightNews = ResourceUiState.Loading) }
        screenModelScope.launch {
            getSpaceFlightUseCase(newsOffset)
                .onSuccess {
                    setState {
                        copy(
                            spaceFlightNews = if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                    list = it
                    Logger.i { "$it" }
                }
                .onFailure {
                    setState { copy(spaceFlightNews = ResourceUiState.Error()) }
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
            copy(spaceFlightNews = ResourceUiState.Success(filteredNews))
        }
    }
}