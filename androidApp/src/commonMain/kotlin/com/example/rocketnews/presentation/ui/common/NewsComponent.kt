package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketnews.domain.model.News
import com.example.rocketnews.helpers.formatNewsDate
import com.example.rocketnews.theme.LocalThemeIsDark
import com.example.rocketnews.theme.md_theme_dark_surface
import com.example.rocketnews.theme.md_theme_light_surface
import com.example.rocketnews.theme.spacing
import com.seiko.imageloader.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsComponent(
    news: News
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(true) }

    val isDark by LocalThemeIsDark.current
    val bgColor = if (isDark) md_theme_dark_surface else md_theme_light_surface

    Box(Modifier.pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            if (dragAmount.y >= 10) {
                showBottomSheet = false
            } else if (dragAmount.y <= 0) {
                showBottomSheet = true
            }
            change.consume()
        }
    }) {
        Box(Modifier.fillMaxSize().background(Color.LightGray))

        ProgressIndicator(Modifier.align(Alignment.Center))

        Image(
            painter = rememberImagePainter(news.url),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = rememberImagePainter(news.hdurl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = bgColor,
                scrimColor = Color.Transparent,
                shape = RoundedCornerShape(16.dp, 16.dp),
                tonalElevation = 0.dp
            ) {
                BottomSheetContent(news)
            }
        }
    }
}

@Composable
fun BottomSheetContent(news: News) {
    Column(
        Modifier.fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.horizontal)
    ) {
        Text(
            news.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Space()
        Text(
            formatNewsDate(news.date),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Space()
        Text(
            news.explanation,
            fontSize = 20.sp,
        )
    }
}
