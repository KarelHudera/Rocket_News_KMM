package karel.hudera.rocketnews.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.helpers.RocketId

@Composable
fun RocketsList(
    rockets: List<Rocket>,
    onRocketClick: (RocketId) -> Unit,
) {
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