package karel.hudera.rocketnews.data_remote.model.apiSpaceFlight

import kotlinx.serialization.Serializable

@Serializable
data class ApiSpaceFlightNews(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)