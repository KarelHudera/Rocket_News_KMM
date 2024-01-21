package com.example.rocketnews.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.rocketnews.presentation.ui.common.Space
import com.example.rocketnews.presentation.ui.screens.news.NewsScreenTab
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsScreenTab
import com.example.rocketnews.presentation.ui.screens.roverInfo.RoverInfoScreenTab
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Star
import compose.icons.fontawesomeicons.solid.PaperPlane
import compose.icons.fontawesomeicons.solid.Star

@Composable
fun MainScreen() {
    TabNavigator(NewsScreenTab) {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier
                        .padding(bottom = 50.dp),
                    content = {
                        CurrentTab()
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.height(80.dp),
                    tonalElevation = 0.dp
                ) {
                    TabNavigationItem(NewsScreenTab)
                    TabNavigationItem(RoverInfoScreenTab)
                    TabNavigationItem(RocketsScreenTab)
                }
            }
        )
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    NavigationBarItem(
        icon = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Space(8.dp)
                tab.options.icon?.let {
                    Icon(
                        painter = if (isSelected) {
                            FilledIcon(tab)
                        } else {
                            it
                        },
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.primary.copy(0.7f)
                        },
                        contentDescription = tab.options.title,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = tab.options.title,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.primary.copy(0.7f)
                    }
                )
            }
        },
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
    )
}

@Composable
fun FilledIcon(item: Tab) = when (item.options.index) {
    (0u).toUShort() -> rememberVectorPainter(FontAwesomeIcons.Solid.Star)
    (1u).toUShort() -> rememberVectorPainter(FontAwesomeIcons.Solid.PaperPlane)
    (2u).toUShort() -> rememberVectorPainter(FontAwesomeIcons.Solid.PaperPlane)
    else -> rememberVectorPainter(FontAwesomeIcons.Regular.Star)
}