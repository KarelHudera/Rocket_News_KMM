package com.example.rocketnews.presentation.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.presentation.ui.common.MainActionAppBar
import com.example.rocketnews.presentation.ui.common.NewsComponent
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsScreen
import kotlinx.coroutines.flow.collectLatest

class NewsScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val newsViewModel = getScreenModel<NewsViewModel>()

        val state by newsViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        val lightBlue = Color(0xFF10aef8)

        LaunchedEffect(key1 = Unit) {
            newsViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is NewsContract.Effect.NavigateToRockets ->
                        navigator.push(RocketsScreen())

                    is  NewsContract.Effect.PickDate -> {
                        newsViewModel.setNewsDatePickerDialog(true)
                    }
                }
            }
        }

        Scaffold(
            topBar = {
                MainActionAppBar(
                    title = "Daily News From NASA",
                    onClickDatePicker = { newsViewModel.setEvent(NewsContract.Event.OnDatePickerClick) },
                )
            },
            floatingActionButton = {
                IconButton(
                    onClick = { newsViewModel.setEvent(NewsContract.Event.OnRocketButtonClick) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(lightBlue)
                        .size(58.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        modifier = Modifier.size(38.dp),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.news,
                successView = { news ->
                    NewsComponent(news, newsViewModel)
                },
                onTryAgain = { newsViewModel.setEvent(NewsContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { newsViewModel.setEvent(NewsContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

