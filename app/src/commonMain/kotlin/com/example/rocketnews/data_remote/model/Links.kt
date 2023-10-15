package com.example.rocketnews.data_remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("article")
    val article: String,
    @SerialName("flickr")
    val flickr: Flickr,
    @SerialName("patch")
    val patch: Patch,
    @SerialName("presskit")
    val presskit: String?,
    @SerialName("reddit")
    val reddit: Reddit,
    @SerialName("webcast")
    val webcast: String,
    @SerialName("wikipedia")
    val wikipedia: String,
    @SerialName("youtube_id")
    val youtubeId: String
)