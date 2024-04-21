package karel.hudera.rocketnews.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Rocket(
    val patchSmall: String, // image
    val patchLarge: String, // image
    val reddit: String,
    val flickr: List<String>, // images
    val webcast: String,
    val youtube_id: String,
    val article: String,
    val wikipedia: String,
    val static_fire_date_utc: String,
    val success: Boolean,
    val details: String,
    val date_utc: String,
    val name: String,
    val upcoming: Boolean,
    val id: String,
)