package com.example.rocketnews.data_remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    val crew: String,
    val role: String
)