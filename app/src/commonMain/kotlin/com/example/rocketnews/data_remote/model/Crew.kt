package com.example.rocketnews.data_remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    @SerialName("crew")
    val crew: String,
    @SerialName("role")
    val role: String
)