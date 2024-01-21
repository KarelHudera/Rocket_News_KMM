package com.example.rocketnews.presentation.ui.common

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.TruncateText
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketActionBar(
    rocket: ResourceUiState<Rocket>,
    favorite: ResourceUiState<Boolean>,
    onActionFavorite: () -> Unit,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            ManagementResourceUiState(
                resourceUiState = rocket,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceFlightNewsActionBar(
    spaceFlightNews: ResourceUiState<SpaceFlightNews>,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            ManagementResourceUiState(
                resourceUiState = spaceFlightNews,
                successView = { Text(text = TruncateText(it.title)) },
                loadingView = { Text(text = "....") },
                onCheckAgain = {},
                onTryAgain = {}
            )
        },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
    )
}
