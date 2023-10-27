package com.example.rocketnews.presentation.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import com.example.rocketnews.presentation.ui.theme.AppTheme
import com.example.rocketnews.presentation.ui.theme.md_theme_dark_onPrimary

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

@Composable
fun MainActionAppBar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primary
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