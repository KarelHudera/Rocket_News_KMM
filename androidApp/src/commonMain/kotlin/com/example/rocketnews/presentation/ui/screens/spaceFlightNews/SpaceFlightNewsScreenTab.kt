package com.example.rocketnews.presentation.ui.screens.spaceFlightNews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.PaperPlane

object SpaceFlightNewsScreenTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(FontAwesomeIcons.Regular.PaperPlane)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Rovers",
                    icon = icon
                )
            }
        }
    @Composable
    override fun Content() {
        Navigator(SpaceFlightNewsScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}