package karel.hudera.rocketnews.data_remote.model.apiSpaceFlight

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val event_id: Int,
    val provider: String
)