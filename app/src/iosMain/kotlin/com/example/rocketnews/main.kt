package com.example.rocketnews

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
