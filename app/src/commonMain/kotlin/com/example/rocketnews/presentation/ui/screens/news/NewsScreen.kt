package com.example.rocketnews.presentation.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
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

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val newsViewModel = getScreenModel<NewsViewModel>()

        val state by newsViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            newsViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is NewsContract.Effect.NavigateToRockets ->
                        navigator.push(RocketsScreen())
                }
            }
        }

        Scaffold(
            topBar = { MainActionAppBar(title = "Daily News From NASA") },
            floatingActionButton = {
                IconButton(
                    onClick = { newsViewModel.setEvent(NewsContract.Event.OnRocketButtonClick) },
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.DarkGray).size(58.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        modifier = Modifier.size(38.dp),
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
                    NewsComponent(news)
                },
                onTryAgain = { newsViewModel.setEvent(NewsContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { newsViewModel.setEvent(NewsContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

