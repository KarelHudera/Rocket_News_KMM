package karel.hudera.rocketnews.data_remote.model.apiNews

import kotlinx.serialization.Serializable

@Serializable
data class ApiNews(
//    val copyright: String, // TODO: sometimes response is without this parameter
    val date: String,
    val explanation: String,
    val hdurl: String, // TODO: sometimes response is without this parameter
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String // TODO: sometimes response is YouTube url
)
