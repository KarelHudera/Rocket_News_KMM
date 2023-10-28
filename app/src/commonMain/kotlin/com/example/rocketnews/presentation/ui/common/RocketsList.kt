package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rocketnews.domain.model.Rocket

@Composable
fun RocketsList(
    rockets: List<Rocket>,
    onRocketClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(rockets) { character ->
            RocketItem(
                rocket = character,
                onClick = { onRocketClick(character.id) }
            )
        }
    }
}