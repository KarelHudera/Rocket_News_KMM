package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.rocketnews.presentation.ui.common.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItemActionBar(
    title: String,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
    )
}