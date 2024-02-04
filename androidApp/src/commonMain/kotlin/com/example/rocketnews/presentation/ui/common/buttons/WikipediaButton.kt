package com.example.rocketnews.presentation.ui.common.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.openUrl
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailContract
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel

@Composable
fun WikipediaButton(rocket: Rocket, rocketDetailViewModel: RocketDetailViewModel) {
    Button(
        onClick = {
            if (rocket.wikipedia != "") {
                openUrl(rocket.wikipedia)
            } else {
                rocketDetailViewModel.setEvent(RocketDetailContract.Event.OnEmptyUrlClick)
            }
        },
        elevation = ButtonDefaults.buttonElevation(1.dp),
        modifier = Modifier.clip(
            RoundedCornerShape(16.dp)
        ).height(38.dp).wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(Color.White),
    ) {
        Text(
            "WIKI",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}