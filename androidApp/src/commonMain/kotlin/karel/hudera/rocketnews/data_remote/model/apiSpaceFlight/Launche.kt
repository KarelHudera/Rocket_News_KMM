package karel.hudera.rocketnews.data_remote.model.apiSpaceFlight

import kotlinx.serialization.Serializable

@Serializable
data class Launche(
    val launch_id: String,
    val provider: String
)