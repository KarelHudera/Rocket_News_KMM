package com.example.rocketnews.presentation.ui.common.topBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.rocketnews.presentation.ui.common.ActionBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketsActionAppBar(
    onClickFavorite: () -> Unit,
    onClickSearch: () -> Unit,
    title: String,
    isShadowEnabled: Boolean = false
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            ActionBarIcon(
                onClick = onClickSearch,
                icon = Icons.Filled.Search
            )
            ActionBarIcon(
                onClick = onClickFavorite,
                icon = Icons.Filled.Favorite
            )
        },
        modifier = if (isShadowEnabled) {
            Modifier.shadow(2.dp)
        } else {
            Modifier
        }
    )
}