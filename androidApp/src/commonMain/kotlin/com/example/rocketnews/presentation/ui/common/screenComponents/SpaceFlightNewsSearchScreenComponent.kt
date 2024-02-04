package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.presentation.ui.common.SearchErrorMessage
import com.example.rocketnews.presentation.ui.common.SpaceflightNewsList
import com.example.rocketnews.presentation.ui.screens.spaceFlightNewsSearch.SpaceFlightNewsSearchContract
import com.example.rocketnews.presentation.ui.screens.spaceFlightNewsSearch.SpaceFlightNewsSearchViewModel

@Composable
fun SpaceFlightNewsSearchScreenComponent(
    news: List<SpaceFlightNews>,
    spaceFlightNewsSearchViewModel: SpaceFlightNewsSearchViewModel,
) {
    var text by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                spaceFlightNewsSearchViewModel.setEvent(
                    SpaceFlightNewsSearchContract.Event.OnSearchTextChanged(it)
                )
            },
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp, start = 16.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Search Spaceflight news") },
        )
        if (news.isEmpty()) {
            SearchErrorMessage(text)
        } else {
            SpaceflightNewsList(
                spaceflightNews = news,
                onSpaceflightNewsClick = { idSpaceFlightNews ->
                    spaceFlightNewsSearchViewModel.setEvent(
                        SpaceFlightNewsSearchContract.Event.OnSpaceFlightNewsClick(
                            idSpaceFlightNews
                        )
                    )
                },
                onLoadMore = {}
            )
        }
    }
}