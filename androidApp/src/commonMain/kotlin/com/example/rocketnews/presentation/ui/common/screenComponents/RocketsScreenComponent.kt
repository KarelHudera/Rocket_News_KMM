package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.helpers.RocketId
import com.example.rocketnews.presentation.ui.common.RocketItem
import com.example.rocketnews.presentation.ui.common.SearchErrorMessage
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsViewModel

@Composable
fun RocketsScreenComponent(
    rockets: List<Rocket>,
    onRocketClick: (RocketId) -> Unit,
    rocketsViewModel: RocketsViewModel
) {
    val searchText = rocketsViewModel.searchText.collectAsState().value

    if (rockets.isEmpty()) {
        SearchErrorMessage(searchText.value.text)
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
        ) {
            items(
                items = rockets,
                key = { it.id }
            ) { rocket ->
                RocketItem(
                    rocket = rocket,
                    onClick = { onRocketClick(rocket.id) }
                )
            }
        }
    }
}