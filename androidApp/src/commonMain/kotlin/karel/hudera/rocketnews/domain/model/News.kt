package karel.hudera.rocketnews.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class News (
    val date: String,
    val explanation: String,
    val hdurl: String, // image
    val title: String,
    val url: String // image
)