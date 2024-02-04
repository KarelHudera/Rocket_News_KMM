package com.example.rocketnews.presentation.ui.common.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.openUrl
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailContract
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel

@Composable
fun YoutubeButton(rocket: Rocket, rocketDetailViewModel: RocketDetailViewModel) {
    IconButton(
        onClick = {
            if (rocket.webcast != "") {
                openUrl(rocket.webcast)
            } else {
                rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnEmptyUrlClick)
            }
        },
        modifier = Modifier.clip(
            RoundedCornerShape(16.dp)
        ).width(56.dp).height(38.dp).background(Color.Red),
    ) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            modifier = Modifier.size(26.dp),
            tint = Color.White,
            contentDescription = null
        )
    }
}