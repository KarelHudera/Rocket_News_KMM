package com.example.rocketnews.presentation.ui.common.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.openUrl

@Composable
fun GoToArticleButton(spaceFlightNew: SpaceFlightNews, modifier: Modifier) {
    Button(
        onClick = {
            openUrl(spaceFlightNew.url)
        },
        elevation = ButtonDefaults.buttonElevation(3.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
    ) {
        Text(
            "Go to: ${spaceFlightNew.news_site}",
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}