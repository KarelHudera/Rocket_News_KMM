package com.example.rocketnews.presentation.ui.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.rocketnews.domain.model.Rocket

@Composable
fun RocketsList(
    rockets: List<Rocket>,
    onRocketClick: (String) -> Unit,
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
    ) {
        itemsIndexed(rockets) { index, rocket ->
            val animatableAlpha = remember { Animatable(0f) }
            val isVisible = remember {
                derivedStateOf {
                    scrollState.firstVisibleItemIndex <= index
                }
            }

            LaunchedEffect(isVisible.value) {
                if (isVisible.value) {
                    animatableAlpha.animateTo(
                        1f, animationSpec = tween(durationMillis = 900)
                    )
                }
            }

            RocketItem(
                rocket = rocket,
                onClick = { onRocketClick(rocket.id) },
                alpha = animatableAlpha.value
            )
        }
    }
}