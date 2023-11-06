package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketnews.domain.model.News
import com.example.rocketnews.helpers.formatNewsDate
import com.example.rocketnews.presentation.ui.theme.spacing
import com.seiko.imageloader.rememberAsyncImagePainter
import kotlin.math.roundToInt

enum class BottomStates {
    EXPANDED,
    HALF_EXPANDED,
    COLLAPSED
}

@ExperimentalMaterialApi
@Composable
fun NewsComponent(
    news: News
) {
    val swipeableState = rememberSwipeableState(initialValue = BottomStates.HALF_EXPANDED)
    val scrollState = rememberScrollState()

    BoxWithConstraints {
        val constraintsScope = this
        val maxHeight = with(LocalDensity.current) {
            constraintsScope.maxHeight.toPx()
        }

        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset, source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipeableState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset, available: Offset, source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipeableState.performDrag(delta).toOffset()
                }

                override suspend fun onPreFling(available: Velocity): Velocity {
                    return if (available.y < 0 && scrollState.value == 0) {
                        swipeableState.performFling(available.y)
                        available
                    } else {
                        Velocity.Zero
                    }
                }

                override suspend fun onPostFling(
                    consumed: Velocity, available: Velocity
                ): Velocity {
                    swipeableState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }

        Box(Modifier.fillMaxSize().background(Color.LightGray))

        ProgressIndicator(Modifier.align(Alignment.Center))

        Image(
            modifier = Modifier.fillMaxSize().scale(LocalDensity.current.density / 1.2f),
            painter = rememberAsyncImagePainter(news.url),
            contentDescription = null
        )

        Image(
            modifier = Modifier.fillMaxSize().scale(LocalDensity.current.density / 1.2f),
            painter = rememberAsyncImagePainter(news.hdurl),
            contentDescription = null
        )

        Box(Modifier.swipeable(
            state = swipeableState, orientation = Orientation.Vertical, anchors = mapOf(
                0f to BottomStates.EXPANDED,
                maxHeight / 1.3f to BottomStates.HALF_EXPANDED,
                maxHeight to BottomStates.COLLAPSED,
            )
        ).nestedScroll(connection).offset {
            IntOffset(
                0, swipeableState.offset.value.roundToInt()
            )
        }) {
            Column(
                Modifier.fillMaxHeight().clip(
                    RoundedCornerShape(16.dp, 16.dp)
                ).background(Color.White)
            ) {
                Column(
                    Modifier.height(26.dp).fillMaxWidth()
                ) {
                    Box(
                        Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.LightGray)
                            .height(6.dp)
                            .width(40.dp)
                    )
                }
                Box(
                    Modifier.fillMaxWidth().verticalScroll(scrollState)
                        .padding(horizontal = MaterialTheme.spacing.horizontal)
                ) {
                    Column {
                        Text(
                            news.title,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black.copy(0.85f)
                        )
                        Space()
                        Text(
                            formatNewsDate(news.date),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black.copy(0.85f)
                        )
                        Space()
                        Text(
                            news.explanation,
                            fontSize = 20.sp,
                            color = Color.Black.copy(0.85f)
                        )
                    }
                }
            }
        }
    }
}