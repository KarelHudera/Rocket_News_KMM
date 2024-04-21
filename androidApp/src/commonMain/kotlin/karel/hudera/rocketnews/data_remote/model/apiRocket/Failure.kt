package karel.hudera.rocketnews.data_remote.model.apiRocket

import kotlinx.serialization.Serializable

@Serializable
data class Failure(
    val altitude: Int?,
    val reason: String,
    val time: Int
)