package com.example.rocketnews.data_remote.model.apiRocket

import kotlinx.serialization.Serializable

@Serializable
data class Patch(
    val large: String?,
    val small: String?
)