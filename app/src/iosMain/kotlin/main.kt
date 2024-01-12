import androidx.compose.ui.window.ComposeUIViewController
import com.example.rocketnews.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
