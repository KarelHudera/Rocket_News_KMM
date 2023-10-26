package com.example.rocketnews.presentation.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable

@Composable
fun FavouriteActionAppBar(
    navIcon: @Composable () -> Unit,
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navIcon,
    )
}

@Composable
fun MainActionAppBar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title) }
    )
}

@Composable
fun RocketsActionAppBar(
    onClickFavorite: () -> Unit,
    onBackPressed: () -> Unit,
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        actions = {
            ActionBarIcon(
                onClick = onClickFavorite,
                icon = Icons.Filled.Favorite
            )
        }
    )
}