package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.rocketnews.presentation.ui.common.ActionBarIcon
import com.example.rocketnews.presentation.ui.common.SearchView
import com.example.rocketnews.presentation.ui.common.Space
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceComponentState
import com.example.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsContract
import com.example.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceFlightNewsActionAppBar(
    title: String,
    isShadowEnabled: Boolean = false,
    spaceFlightNewsViewModel: SpaceFlightNewsViewModel
) {
    val state = spaceFlightNewsViewModel.uiState.collectAsState().value
    val showSearch = spaceFlightNewsViewModel.showSearchBar.collectAsState().value
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    AnimatedContent(
        targetState = showSearch,
        transitionSpec = {
            (fadeIn() + slideInHorizontally(animationSpec = tween(200),
                initialOffsetX = { fullWidth -> fullWidth })).togetherWith(
                fadeOut(
                    animationSpec = tween(
                        200
                    )
                )
            )
        }
    ) { searchVisible ->
        if (searchVisible) {
            TopAppBar(
                title = {},
                actions = {
                    SearchView(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 14.dp, end = 10.dp),
                        placeholder = "Search News",
                        textState = textState,
                        onValueChange = {
                            spaceFlightNewsViewModel.setEvent(
                                SpaceFlightNewsContract.Event.OnSearchTextChanged(it)
                            )
                        },
                        onBackClick = {
                            spaceFlightNewsViewModel.setEvent(
                                SpaceFlightNewsContract.Event.OnSearchTextChanged("")
                            )
                            spaceFlightNewsViewModel.setEvent(SpaceFlightNewsContract.Event.OnBackClick)
                            textState.value = TextFieldValue("")
                        },
                        onTrailingIconClick = {
                            spaceFlightNewsViewModel.setEvent(
                                SpaceFlightNewsContract.Event.OnSearchTextChanged("")
                            )
                            textState.value = TextFieldValue("")
                        }
                    )
                },
                modifier = if (isShadowEnabled) {
                    Modifier.shadow(2.dp)
                } else {
                    Modifier
                },
            )
        } else {
            TopAppBar(
                title = { Text(text = title) },
                actions = {
                    ManagementResourceComponentState(
                        resourceUiState = state.spaceFlightNews,
                        successView = {
                            ActionBarIcon(
                                onClick = {
                                    spaceFlightNewsViewModel.setEvent(
                                        SpaceFlightNewsContract.Event.OnSearchClick
                                    )
                                },
                                icon = Icons.Filled.Search
                            )
                        },
                        loadingView = { Space() }
                    )
                },
                modifier = if (isShadowEnabled) {
                    Modifier.shadow(2.dp)
                } else {
                    Modifier
                }
            )
        }
    }
}