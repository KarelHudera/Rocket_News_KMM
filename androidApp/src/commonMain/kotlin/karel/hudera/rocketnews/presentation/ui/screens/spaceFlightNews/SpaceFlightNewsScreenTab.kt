package karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource

object SpaceFlightNewsScreenTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource("world_outlined.xml")

            return remember {
                TabOptions(
                    index = 1u,
                    title = "News",
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