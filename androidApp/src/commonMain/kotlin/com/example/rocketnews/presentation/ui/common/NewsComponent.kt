package com.example.rocketnews.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketnews.domain.model.News
import com.example.rocketnews.helpers.formatNewsDate
import com.example.rocketnews.presentation.ui.screens.news.NewsViewModel
import com.example.rocketnews.theme.LocalThemeIsDark
import com.example.rocketnews.theme.md_theme_dark_surface
import com.example.rocketnews.theme.md_theme_light_surface
import com.example.rocketnews.theme.spacing
import com.seiko.imageloader.rememberImagePainter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsComponent(
    news: News,
    newsViewModel: NewsViewModel
) {
    val sheetState = rememberModalBottomSheetState()

    val isDark by LocalThemeIsDark.current
    val bgColor = if (isDark) md_theme_dark_surface else md_theme_light_surface


    val newsDatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds(),
    )

    val showNewsDatePickerDialog = newsViewModel.showNewsDatePickerDialog.collectAsState().value

    val showNewsInfoDialog = newsViewModel.showNewsInfoDialog.collectAsState().value


    Box {
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

        if (showNewsDatePickerDialog) {
            NewsDatePicker(
                datePickerState = newsDatePickerState,
                dismiss = {
                    newsViewModel.setNewsDatePickerDialog(false)
                },
                onConfirmDate = {
                    newsViewModel.setNewsDatePickerDialog(false)
                    // TODO("implement fun")
                },
            )
        }

        if (showNewsInfoDialog) {
            ModalBottomSheet(
                onDismissRequest = {
                    newsViewModel.setNewsInfoDialog(false)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDatePicker(
    datePickerState: DatePickerState,
    dismiss: () -> Unit,
    onConfirmDate: (LocalDateTime) -> Unit,
) {
    DatePickerDialog(
        onDismissRequest = { dismiss() },
        dismissButton = {
            TextButton(onClick = dismiss) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmDate(datePickerState.selectedDateMillis.selectedDateMillisToLocalDateTime())
                    dismiss()
                },
            ) {
                Text(text = "OK")
            }
        },
    ) {
        DatePicker(state = datePickerState)
    }
}

fun Long?.selectedDateMillisToLocalDateTime(): LocalDateTime {
    return Instant.fromEpochMilliseconds(this ?: 0)
        .toLocalDateTime(TimeZone.currentSystemDefault())
}
