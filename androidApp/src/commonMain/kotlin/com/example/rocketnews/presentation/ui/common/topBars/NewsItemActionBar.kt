package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.rocketnews.presentation.ui.common.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItemActionBar(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {},
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        colors = TopAppBarDefaults.topAppBarColors(androidx.compose.ui.graphics.Color.Transparent)
    )
}