package karel.hudera.rocketnews

import androidx.compose.runtime.Composable
import karel.hudera.rocketnews.presentation.ui.screens.MainScreen
import karel.hudera.rocketnews.presentation.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    MainScreen()
}

internal expect fun openUrl(url: String?)