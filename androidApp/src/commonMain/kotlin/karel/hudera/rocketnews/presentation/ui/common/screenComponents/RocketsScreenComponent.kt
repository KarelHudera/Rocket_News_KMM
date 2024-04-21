package karel.hudera.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.helpers.RocketId
import karel.hudera.rocketnews.presentation.ui.common.RocketItem
import karel.hudera.rocketnews.presentation.ui.common.SearchErrorMessage
import karel.hudera.rocketnews.presentation.ui.screens.rockets.RocketsViewModel

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