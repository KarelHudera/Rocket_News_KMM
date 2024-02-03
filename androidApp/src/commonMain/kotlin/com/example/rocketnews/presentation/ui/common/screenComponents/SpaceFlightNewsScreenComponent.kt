package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.runtime.Composable
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.presentation.ui.common.SpaceflightNewsList
import com.example.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsContract
import com.example.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsViewModel

@Composable
fun SpaceFlightNewsScreenComponent(
    spaceflightNews: List<SpaceFlightNews>,
    spaceFlightNewsViewModel: SpaceFlightNewsViewModel
) {
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