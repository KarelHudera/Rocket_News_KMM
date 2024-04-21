package karel.hudera.rocketnews.presentation.ui.screens.newsImage

import karel.hudera.rocketnews.presentation.mvi.BaseViewModel

class NewsImageViewModel :
    BaseViewModel<NewsImageContract.Event, NewsImageContract.State, NewsImageContract.Effect>() {
    override fun createInitialState(): NewsImageContract.State =
        NewsImageContract.State(
            state = Unit
        )

    override fun handleEvent(event: NewsImageContract.Event) {
        when (event) {
            is NewsImageContract.Event.OnBackPressed ->
                setEffect { NewsImageContract.Effect.BackNavigation }
        }
    }
}