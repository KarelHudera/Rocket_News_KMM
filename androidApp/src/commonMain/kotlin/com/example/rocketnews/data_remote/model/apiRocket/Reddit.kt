package com.example.rocketnews.data_remote.model.apiRocket

import kotlinx.serialization.Serializable

@Serializable
data class Reddit(
    val campaign: String?,
    val launch: String?,
    val media: String?,
    val recovery: String?
)