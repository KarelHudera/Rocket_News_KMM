package karel.hudera.rocketnews.presentation.ui.screens.rockets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource

object RocketsScreenTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource("rocket_outlined.xml")

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