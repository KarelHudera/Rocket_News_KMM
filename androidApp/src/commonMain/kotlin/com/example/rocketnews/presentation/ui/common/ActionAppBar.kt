package com.example.rocketnews.presentation.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackNavActionAppBar(
    navIcon: @Composable () -> Unit,
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navIcon,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActionAppBar(
    title: String,
    onClickDatePicker: () -> Unit

    ) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            ActionBarIcon(
                onClick = onClickDatePicker,
                icon = Icons.Filled.Menu
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketsActionAppBar(
    onClickFavorite: () -> Unit,
    onClickSearch: () -> Unit,
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            ActionBarIcon(
                onClick = onClickSearch,
                icon = Icons.Filled.Search
            )
            ActionBarIcon(
                onClick = onClickFavorite,
                icon = Icons.Filled.Favorite
            )
        }
    )
}