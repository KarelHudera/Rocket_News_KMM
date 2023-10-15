package com.example.rocketnews.data_remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reddit(
    @SerialName("campaign")
    val campaign: String?,
    @SerialName("launch")
    val launch: String?,
    @SerialName("media")
    val media: String?,
    @SerialName("recovery")
    val recovery: String?
)