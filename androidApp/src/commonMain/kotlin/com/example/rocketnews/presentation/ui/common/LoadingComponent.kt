package com.example.rocketnews.presentation.ui.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingComponent(
    cornerShape: Dp = 0.dp,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(cornerShape))
            .background(Color.LightGray)
            .shimmerLoadingAnimation()
    )
}

fun Modifier.shimmerLoadingAnimation(
    widthOfShadowBrush: Int = 600,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 2000,
): Modifier {
    return composed {

        val shimmerColors = listOf(
            Color.Gray.copy(alpha = 0.2f),
            Color.Gray.copy(alpha = 0.4f),
            Color.Gray.copy(alpha = 0.2f),
        )

        val transition = rememberInfiniteTransition(label = "")

        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            label = "Shimmer loading animation",
        )

        this.background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            ),
        )
    }
}