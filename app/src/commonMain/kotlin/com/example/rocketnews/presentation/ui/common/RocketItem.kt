package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.presentation.ui.theme.spacing
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun RocketItem(
    rocket: Rocket, onClick: () -> Unit
) {


    Card(
        Modifier.padding(vertical = 8.dp, horizontal = MaterialTheme.spacing.horizontal)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onClick).fillMaxWidth()
        ) {
            Box {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
                Image(
                    painter = rememberAsyncImagePainter(rocket.patchSmall),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp).width(110.dp).height(110.dp)
                )
                Image(
                    painter = rememberAsyncImagePainter(rocket.patchLarge),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp).width(110.dp).height(110.dp)
                )
            }
            Space()
            Column {
                Text(
                    text = rocket.name,
                    color = Color.Black.copy(0.85f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Space()
                Row {
                    IconButton(
                        onClick = { (rocket.youtube_id) }, //TODO: youtube
                        modifier = Modifier.clip(
                            RoundedCornerShape(16.dp)
                        ).width(56.dp).height(40.dp).background(Color.Red),
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                    Space()
                    Button(
                        onClick = { rocket.wikipedia }, //TODO: wikipedia
                        modifier = Modifier.clip(
                            RoundedCornerShape(16.dp)
                        ).height(40.dp).wrapContentWidth(),
                        colors = ButtonDefaults.buttonColors(Color.White),
                    ) {
                        Text(
                            "WIKI", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}