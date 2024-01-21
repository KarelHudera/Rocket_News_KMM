package com.example.rocketnews.presentation.ui.screens.rockets

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

object RocketsScreenTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(FontAwesomeIcons.Regular.PaperPlane)

            return remember {
                TabOptions(
                    index = 2u,
                    title = "Rockets",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(RocketsScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}