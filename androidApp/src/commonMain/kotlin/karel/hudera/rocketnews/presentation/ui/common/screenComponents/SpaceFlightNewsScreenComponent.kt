package karel.hudera.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsContract
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.presentation.ui.common.SearchErrorMessage
import karel.hudera.rocketnews.presentation.ui.common.SpaceflightNewsList
import karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsViewModel

@Composable
fun SpaceFlightNewsScreenComponent(
    spaceflightNews: List<SpaceFlightNews>,
    spaceFlightNewsViewModel: SpaceFlightNewsViewModel
) {
    val searchText = spaceFlightNewsViewModel.searchText.collectAsState().value

    if (spaceflightNews.isEmpty()) {
        SearchErrorMessage(searchText.value.text)
    } else {
        SpaceflightNewsList(
            spaceflightNews = spaceflightNews,
            onSpaceflightNewsClick = { idSpaceFlightNews ->
                spaceFlightNewsViewModel.setEvent(
                    SpaceFlightNewsContract.Event.OnSpaceFlightNewsClick(
                        idSpaceFlightNews
                    )
                )
            },
            onLoadMore = {
                //spaceFlightNewsViewModel.loadMoreSpaceFLightNews()
            }
        )
    }
}