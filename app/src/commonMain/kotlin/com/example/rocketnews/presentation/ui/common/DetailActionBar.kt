package com.example.rocketnews.presentation.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState

@Composable
fun ActionBar(
    character: ResourceUiState<Rocket>,
    favorite: ResourceUiState<Boolean>,
    onActionFavorite: () -> Unit,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            ManagementResourceUiState(
                resourceUiState = character,
                successView = { Text(text = it.name) },
                loadingView = { Text(text = "....") },
                onCheckAgain = {},
                onTryAgain = {}
            )
        },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        actions = {
            ManagementResourceUiState(
                resourceUiState = favorite,
                successView = {
                    ActionBarIcon(
                        onClick = onActionFavorite,
                        icon = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                    )
                },
                loadingView = {
                    ActionBarIcon(
                        enabled = false,
                        onClick = {},
                        icon = Icons.Filled.Favorite
                    )
                },
                onCheckAgain = {},
                onTryAgain = {}
            )
        }
    )
}

