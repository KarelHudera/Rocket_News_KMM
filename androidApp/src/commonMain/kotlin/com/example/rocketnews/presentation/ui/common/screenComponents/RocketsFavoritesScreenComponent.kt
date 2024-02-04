package com.example.rocketnews.presentation.ui.common.screenComponents

import androidx.compose.runtime.Composable
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.ui.common.RocketsList
import com.example.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesContract
import com.example.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesViewModel

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