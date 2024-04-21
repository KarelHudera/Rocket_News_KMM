package karel.hudera.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.runtime.Composable
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.presentation.ui.common.RocketsList
import karel.hudera.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesContract
import karel.hudera.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesViewModel

@Composable
fun RocketsFavoritesScreenComponent(
    favorites: List<Rocket>,
    rocketsFavoritesViewModel: RocketsFavoritesViewModel
) {
    RocketsList(
        rockets = favorites,
        onRocketClick = { idRocket ->
            rocketsFavoritesViewModel.setEvent(
                RocketsFavoritesContract.Event.OnRocketClick(
                    idRocket
                )
            )
        }
    )
}