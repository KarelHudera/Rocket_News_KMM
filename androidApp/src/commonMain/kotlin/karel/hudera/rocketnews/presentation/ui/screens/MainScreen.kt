package karel.hudera.rocketnews.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import karel.hudera.rocketnews.presentation.ui.screens.news.NewsScreenTab
import karel.hudera.rocketnews.presentation.ui.screens.rockets.RocketsScreenTab
import karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsScreenTab
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen() {
    TabNavigator(NewsScreenTab) {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier
                        .padding(bottom = 60.dp),
                    content = {
                        CurrentTab()
                    }
                )
            },
            bottomBar = {
                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height(62.dp)
                ) {
                    Divider(
                        color = Color.Transparent,
                        thickness = 0.dp,
                        modifier = Modifier.shadow(1.dp, spotColor = Color.Black)
                    )
                    NavigationBar(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .height(62.dp),
                        tonalElevation = 0.dp
                    ) {
                        TabNavigationItem(NewsScreenTab)
                        TabNavigationItem(SpaceFlightNewsScreenTab)
                        TabNavigationItem(RocketsScreenTab)
                    }
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
                modifier = Modifier.width(52.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
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
        onClick = { tabNavigator.current = tab }
    )
}

@Composable
fun FilledIcon(item: Tab) = when (item.options.index) {
    (0u).toUShort() -> painterResource("sparkles_filled.xml")
    (1u).toUShort() -> painterResource("world_filled.xml")
    (2u).toUShort() -> painterResource("rocket_filled.xml")
    else -> painterResource("sparkles_filled.xml")
}