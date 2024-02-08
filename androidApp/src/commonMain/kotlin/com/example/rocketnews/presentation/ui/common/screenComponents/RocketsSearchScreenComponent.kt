package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.ui.common.RocketsList
import com.example.rocketnews.presentation.ui.common.SearchErrorMessage
import com.example.rocketnews.presentation.ui.screens.rocketsSearch.RocketsSearchContract
import com.example.rocketnews.presentation.ui.screens.rocketsSearch.RocketsSearchViewModel

@Composable
fun RocketsSearchScreenComponent(
    rockets: List<Rocket>,
    rocketsSearchViewModel: RocketsSearchViewModel
) {
    var text by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                rocketsSearchViewModel.setEvent(
                    RocketsSearchContract.Event.OnSearchTextChanged(it)
                )
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(bottom = 4.dp, end = 16.dp, start = 16.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Search rocket") },
        )
        Divider(color = Color.LightGray, thickness = 0.dp, modifier = Modifier.shadow(2.dp))
        if (rockets.isEmpty()) {
            SearchErrorMessage(text)
        } else {
            RocketsList(
                rockets = rockets,
                onRocketClick = { idRocket ->
                    rocketsSearchViewModel.setEvent(
                        RocketsSearchContract.Event.OnRocketClick(idRocket)
                    )
                }
            )
        }
    }
}