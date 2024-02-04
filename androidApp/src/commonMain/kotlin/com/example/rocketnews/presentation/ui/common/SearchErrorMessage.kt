package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SearchErrorMessage(text: String) {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Column {
            Text(
                text = "Couldn't find \"$text\"",
                style = MaterialTheme.typography.headlineMedium
            )
            Space()
            Text(
                text = "Try something else",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}