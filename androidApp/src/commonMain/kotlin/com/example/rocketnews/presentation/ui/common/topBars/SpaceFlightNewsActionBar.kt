package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.TruncateText
import com.example.rocketnews.presentation.model.ResourceUiState
import com.example.rocketnews.presentation.ui.common.ArrowBackIcon
import com.example.rocketnews.presentation.ui.common.state.ManagementResourceComponentState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceFlightNewsActionBar(
    spaceFlightNews: ResourceUiState<SpaceFlightNews>,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            ManagementResourceComponentState(
                resourceUiState = spaceFlightNews,
                successView = { Text(text = TruncateText(it.title)) },
                loadingView = { Text(text = "....") }
            )
        },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
    )
}