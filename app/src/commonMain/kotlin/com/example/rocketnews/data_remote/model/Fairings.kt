package com.example.rocketnews.data_remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fairings(
    @SerialName("recovered")
    val recovered: Boolean?,
    @SerialName("recovery_attempt")
    val recoveryAttempt: Boolean?,
    @SerialName("reused")
    val reused: Boolean?,
    @SerialName("ships")
    val ships: List<String>?
)