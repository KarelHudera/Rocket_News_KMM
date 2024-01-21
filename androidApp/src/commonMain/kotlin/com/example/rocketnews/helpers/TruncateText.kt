package com.example.rocketnews.helpers

import androidx.compose.runtime.Composable

@Composable
fun TruncateText(text: String, maxLength: Int = 30): String {
    val truncatedText = if (text.length > maxLength) {
        text.substring(0, maxLength) + "..."
    } else {
        text
    }
    return truncatedText
}