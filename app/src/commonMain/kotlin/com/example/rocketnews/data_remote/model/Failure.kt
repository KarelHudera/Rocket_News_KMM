package com.example.rocketnews.data_remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Failure(
    val altitude: Int?,
    val reason: String,
    val time: Int
)