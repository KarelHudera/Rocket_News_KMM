package com.example.rocketnews.presentation.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BackNavActionAppBar(
    navIcon: @Composable () -> Unit,
    title: String
) {
    TopAppBar(
        backgroundColor = Color(0xFF434343),
        contentColor = Color.White,
        title = { Text(text = title) },
        navigationIcon = navIcon,
    )
}

@Composable
fun MainActionAppBar(
    title: String
) {
    TopAppBar(
        backgroundColor = Color(0xFF434343),
        contentColor = Color.White,
        title = { Text(text = title) },
    )
}

@Composable
fun RocketsActionAppBar(
    onClickFavorite: () -> Unit,
    onClickSearch: () -> Unit,
    onBackPressed: () -> Unit,
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = Color(0xFF434343),
        contentColor = Color.White,
        navigationIcon = { ArrowBackIcon(onBackPressed) },
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