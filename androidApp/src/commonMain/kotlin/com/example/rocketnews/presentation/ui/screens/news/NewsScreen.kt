package com.example.rocketnews.presentation.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
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
import com.example.rocketnews.presentation.ui.common.MainActionAppBar
import com.example.rocketnews.presentation.ui.common.NewsComponent
import com.example.rocketnews.presentation.ui.common.NewsDatePicker
import com.example.rocketnews.presentation.ui.common.Space
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceComponentState
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.Clock

class NewsScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val newsViewModel = getScreenModel<NewsViewModel>()

        val state by newsViewModel.uiState.collectAsState()

        val newsDatePickerState = rememberDatePickerState(
            initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds(),
        )

        val showNewsDatePickerDialog = newsViewModel.showNewsDatePickerDialog.collectAsState().value

        val lightBlue = Color(0xFF10aef8)

        LaunchedEffect(key1 = Unit) {
            newsViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is NewsContract.Effect.NavigateToRockets ->
                        newsViewModel.setNewsInfoDialog(true)

                    is NewsContract.Effect.PickDate -> {
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
                ManagementResourceComponentState(
                    resourceUiState = state.news,
                    successView = {
                        IconButton(
                            onClick = { newsViewModel.setEvent(NewsContract.Event.OnRocketButtonClick) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .background(lightBlue)
                                .size(58.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                modifier = Modifier.size(38.dp),
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                    },
                    loadingView = { Space() },

                )
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
            if (showNewsDatePickerDialog) {
                NewsDatePicker(
                    datePickerState = newsDatePickerState,
                    dismiss = {
                        newsViewModel.setNewsDatePickerDialog(false)
                    },
                    onConfirmDate = {
                        newsViewModel.setNewsDatePickerDialog(false)
                        newsViewModel.setNewsDateFromDatePicker(it.date)
                    }
                )
            }
        }
    }
}