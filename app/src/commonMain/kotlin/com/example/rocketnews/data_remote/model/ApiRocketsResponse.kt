package com.example.rocketnews.data_remote.model

import com.example.rocketnews.data_remote.model.ApiRocket
import kotlinx.serialization.Serializable

@Serializable
data class ApiRocketsResponse(
    val results: List<ApiRocket>
)