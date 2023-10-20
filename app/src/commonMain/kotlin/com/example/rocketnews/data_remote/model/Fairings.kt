package com.example.rocketnews.data_remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Fairings(
    val recovered: Boolean?,
    val recovery_attempt: Boolean?,
    val reused: Boolean?,
    val ships: List<String>
)