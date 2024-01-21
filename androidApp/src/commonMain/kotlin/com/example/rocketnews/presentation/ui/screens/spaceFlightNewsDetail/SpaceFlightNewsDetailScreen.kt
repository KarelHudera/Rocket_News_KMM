package com.example.rocketnews.presentation.ui.screens.spaceFlightNewsDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.rocketnews.openUrl
import com.example.rocketnews.presentation.ui.common.ProgressIndicator
import com.example.rocketnews.presentation.ui.common.Space
import com.example.rocketnews.presentation.ui.common.SpaceFlightNewsActionBar
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf


class SpaceFlightNewsDetailScreen(
    private val spaceFlightNewsId: String
) : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        val spaceFlightNewsDetailViewModel =
            getScreenModel<SpaceFlightNewsDetailViewModel> { parametersOf(spaceFlightNewsId) }

        val state by spaceFlightNewsDetailViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            spaceFlightNewsDetailViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is SpaceFlightNewsDetailContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                SpaceFlightNewsActionBar(
                    spaceFlightNews = state.spaceFlightNew,
                    onBackPressed = {
                        spaceFlightNewsDetailViewModel.setEvent(
                            SpaceFlightNewsDetailContract.Event.OnBackPressed
                        )
                    }
                )
            },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.spaceFlightNew,
                successView = { spaceFlightNew ->
                    SpaceFlightNewsDetailComponent(spaceFlightNew)
                },
                onTryAgain = { spaceFlightNewsDetailViewModel.setEvent(SpaceFlightNewsDetailContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = {
                    spaceFlightNewsDetailViewModel.setEvent(
                        SpaceFlightNewsDetailContract.Event.OnTryCheckAgainClick
                    )
                },
            )
        }
    }
}

@Composable
fun SpaceFlightNewsDetailComponent(spaceFlightNew: SpaceFlightNews) {
    Box(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box {
                Box(Modifier.fillMaxSize().background(Color.LightGray))

                ProgressIndicator(Modifier.align(Alignment.Center))

                Image(
                    painter = rememberImagePainter(spaceFlightNew.image_url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                )
            }

            Column(Modifier.padding(8.dp)) {
                Space()
                Text(
                    text = formatRocketsDate(spaceFlightNew.published_at),
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Space()
                Text(
                    text = spaceFlightNew.summary,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Space(120.dp)
            }
        }
        GoToArticleButton(
            spaceFlightNew = spaceFlightNew,
            modifier = Modifier.padding(bottom = 24.dp).align(Alignment.BottomCenter).clip(
                RoundedCornerShape(16.dp)
            ).height(38.dp).wrapContentWidth()
        )
    }
}

@Composable
fun GoToArticleButton(spaceFlightNew: SpaceFlightNews, modifier: Modifier) {
    Button(
        onClick = {
            openUrl(spaceFlightNew.url)
        },
        elevation = ButtonDefaults.buttonElevation(3.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
    ) {
        Text(
            "Go to: ${spaceFlightNew.news_site}",
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}