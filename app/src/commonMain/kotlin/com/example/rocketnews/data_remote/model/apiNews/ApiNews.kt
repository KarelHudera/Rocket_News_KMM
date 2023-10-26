package com.example.rocketnews.data_remote.model.apiNews

import kotlinx.serialization.Serializable

@Serializable
data class ApiNews(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)
