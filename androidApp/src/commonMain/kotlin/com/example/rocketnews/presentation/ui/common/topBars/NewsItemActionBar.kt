package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.rocketnews.presentation.ui.common.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItemActionBar(
    onBackPressed: () -> Unit,
    isShadowEnabled: Boolean = false
) {
    TopAppBar(
        title = {},
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        colors = TopAppBarDefaults.topAppBarColors(androidx.compose.ui.graphics.Color.Transparent),
        modifier = if (isShadowEnabled) {
            Modifier.shadow(2.dp)
        } else {
            Modifier
        }
    )
}