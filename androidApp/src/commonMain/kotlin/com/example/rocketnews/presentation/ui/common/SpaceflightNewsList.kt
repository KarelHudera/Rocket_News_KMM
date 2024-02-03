package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rocketnews.domain.model.SpaceFlightNews

@Composable
fun SpaceflightNewsList(
    spaceflightNews: List<SpaceFlightNews>,
    onSpaceflightNewsClick: (String) -> Unit,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
    ) {
        items(spaceflightNews) { spaceflightNews ->
            SpaceFlightNewsItem(
                spaceFlightNews = spaceflightNews,
                onClick = { onSpaceflightNewsClick(spaceflightNews.id.toString()) }
            )
        }
    }
    InfiniteListHandler(listState = listState) {
        onLoadMore()
    }
}