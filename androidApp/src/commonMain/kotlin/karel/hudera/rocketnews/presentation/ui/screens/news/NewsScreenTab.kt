package karel.hudera.rocketnews.presentation.ui.screens.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource

object NewsScreenTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource("sparkles_outlined.xml")

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