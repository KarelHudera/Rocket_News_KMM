package com.example.rocketnews.presentation.ui.screens.spaceFlightNews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.formatRocketsDate
import com.example.rocketnews.presentation.ui.common.ProgressIndicator
import com.example.rocketnews.presentation.ui.common.Space
import com.example.rocketnews.presentation.ui.common.SpaceFlightNewsActionAppBar
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.screens.spaceFlightNewsDetail.SpaceFlightNewsDetailScreen
import com.example.rocketnews.theme.spacing
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class SpaceFlightNewsScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val spaceFlightNewsViewModel = getScreenModel<SpaceFlightNewsViewModel>()

        val state by spaceFlightNewsViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            spaceFlightNewsViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is SpaceFlightNewsContract.Effect.NavigateToDetailSpaceFlightNews ->
                        navigator.push(SpaceFlightNewsDetailScreen(effect.idSpaceFlightNews))
                }
            }
        }

        Scaffold(
            topBar = {
                SpaceFlightNewsActionAppBar(
                    title = "Daily News From Spaceflight",
                )
            },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.spaceFlightNews,
                successView = { spaceflightNews ->
                    spaceflightNewsList(
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
                },
                onTryAgain = { spaceFlightNewsViewModel.setEvent(SpaceFlightNewsContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { spaceFlightNewsViewModel.setEvent(SpaceFlightNewsContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

@Composable
fun spaceflightNewsList(
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

@Composable
fun SpaceFlightNewsItem(
    spaceFlightNews: SpaceFlightNews,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = MaterialTheme.spacing.horizontal),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(Modifier.clickable(onClick = onClick)) {
            Box {
                Box(Modifier.fillMaxSize().background(Color.LightGray))

                ProgressIndicator(Modifier.align(Alignment.Center))

                Image(
                    painter = rememberImagePainter(spaceFlightNews.image_url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                )
            }

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = spaceFlightNews.title,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Space()
                Text(
                    text = formatRocketsDate(spaceFlightNews.published_at),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
fun InfiniteListHandler(
    listState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { Pair(loadMore.value, listState.layoutInfo.totalItemsCount) }
            .distinctUntilChanged()
            .collect {
                onLoadMore()
            }
    }
}