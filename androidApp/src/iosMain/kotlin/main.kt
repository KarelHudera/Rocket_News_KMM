import androidx.compose.ui.window.ComposeUIViewController
import karel.hudera.rocketnews.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
