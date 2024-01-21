package com.example.rocketnews.presentation.ui.screens.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Star

object NewsScreenTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(FontAwesomeIcons.Regular.Star)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "APOD",
                    icon = icon,
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(NewsScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}