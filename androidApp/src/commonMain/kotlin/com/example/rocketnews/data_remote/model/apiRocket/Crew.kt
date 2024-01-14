package com.example.rocketnews.data_remote.model.apiRocket

import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    val crew: String,
    val role: String
)