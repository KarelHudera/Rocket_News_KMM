package karel.hudera.rocketnews.data_remote.model.apiSpaceFlight

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val events: List<Event>,
    val featured: Boolean,
    val id: Int,
    val image_url: String,
    val launches: List<Launche>,
    val news_site: String,
    val published_at: String,
    val summary: String,
    val title: String,
    val updated_at: String,
    val url: String
)