package com.example.rocketnews.presentation.ui.screens.newsImage

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rocketnews.presentation.ui.common.screenComponents.NewsImageScreenComponent
import com.example.rocketnews.presentation.ui.common.topBars.NewsItemActionBar
import kotlinx.coroutines.flow.collectLatest

class NewsImageScreen(
    private val url: String,
    private val hdUrl: String
) : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val newsImageViewModel = getScreenModel<NewsImageViewModel>()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            newsImageViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is NewsImageContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                NewsItemActionBar(
                    onBackPressed = {
                        newsImageViewModel.setEvent(
                            NewsImageContract.Event.OnBackPressed
                        )
                    }
                )
            }
        ) {
           NewsImageScreenComponent(url = url, hdUrl = hdUrl)
        }
    }
}