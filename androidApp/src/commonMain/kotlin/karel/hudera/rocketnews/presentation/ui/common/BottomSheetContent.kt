package karel.hudera.rocketnews.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.helpers.formatNewsDate
import karel.hudera.rocketnews.presentation.theme.spacing

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