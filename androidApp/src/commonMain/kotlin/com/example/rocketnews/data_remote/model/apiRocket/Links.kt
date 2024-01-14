package com.example.rocketnews.data_remote.model.apiRocket

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val article: String?,
    val flickr: Flickr,
    val patch: Patch,
    val presskit: String?,
    val reddit: Reddit,
    val webcast: String?,
    val wikipedia: String?,
    val youtube_id: String?
)