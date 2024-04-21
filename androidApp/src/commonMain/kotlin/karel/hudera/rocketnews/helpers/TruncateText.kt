package karel.hudera.rocketnews.helpers

import androidx.compose.runtime.Composable

@Composable
fun TruncateText(text: String, maxLength: Int = 28): String {
    val truncatedText = if (text.length > maxLength) {
        text.substring(0, maxLength) + "..."
    } else {
        text
    }
    return truncatedText
}