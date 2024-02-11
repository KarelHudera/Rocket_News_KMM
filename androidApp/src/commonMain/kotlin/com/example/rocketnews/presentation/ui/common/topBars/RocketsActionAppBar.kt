package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.rocketnews.presentation.ui.common.ActionBarIcon
import com.example.rocketnews.presentation.ui.common.LoadingComponent
import com.example.rocketnews.presentation.ui.common.SearchView
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceComponentState
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsContract
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketsActionAppBar(
    title: String,
    rocketsViewModel: RocketsViewModel,
    isShadowEnabled: Boolean = false
) {
    val showSearch = rocketsViewModel.showSearchBar.collectAsState().value
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val state by rocketsViewModel.uiState.collectAsState()

    if (showSearch) {
        TopAppBar(
            title = {},
            actions = {
                SearchView(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 14.dp, end = 10.dp),
                    placeholder = "Search rockets",
                    textState = textState,
                    onValueChange = {
                        rocketsViewModel.setEvent(
                            RocketsContract.Event.OnSearchTextChanged(it)
                        )
                    },
                    onBackClick = {
                        rocketsViewModel.setEvent(
                            RocketsContract.Event.OnSearchTextChanged("")
                        )
                        rocketsViewModel.setEvent(RocketsContract.Event.OnBackClick)
                        textState.value = TextFieldValue("")
                    },
                    onTrailingIconClick = {
                        rocketsViewModel.setEvent(
                            RocketsContract.Event.OnSearchTextChanged("")
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
                    resourceUiState = state.rockets,
                    successView = {
                        ActionBarIcon(
                            onClick = { rocketsViewModel.setEvent(RocketsContract.Event.OnSearchClick) },
                            icon = Icons.Filled.Search
                        )
                    },
                    loadingView = {
                        LoadingComponent(
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(22.dp),
                            cornerShape = 16.dp
                        )
                    }
                )
                ActionBarIcon(
                    onClick = { rocketsViewModel.setEvent(RocketsContract.Event.OnFavoritesClick) },
                    icon = Icons.Filled.Favorite
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