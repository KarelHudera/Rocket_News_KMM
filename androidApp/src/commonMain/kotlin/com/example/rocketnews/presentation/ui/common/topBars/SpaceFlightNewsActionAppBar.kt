package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.rocketnews.presentation.ui.common.ActionBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceFlightNewsActionAppBar(
    title: String,
    onClickSearch: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            ActionBarIcon(
                onClick = onClickSearch,
                icon = Icons.Filled.Search
            )
        }
    )
}