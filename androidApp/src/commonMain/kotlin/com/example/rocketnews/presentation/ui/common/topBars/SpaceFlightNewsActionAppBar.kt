package com.example.rocketnews.presentation.ui.common.topBars

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
import com.example.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsContract
import com.example.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceFlightNewsActionAppBar(
    title: String,
    onClickSearch: () -> Unit,
    isShadowEnabled: Boolean = false,
    spaceFlightNewsViewModel: SpaceFlightNewsViewModel
) {
    val showSearch = spaceFlightNewsViewModel.showSearchBar.collectAsState().value
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    if (showSearch) {
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
                ActionBarIcon(
                    onClick = onClickSearch,
                    icon = Icons.Filled.Search
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