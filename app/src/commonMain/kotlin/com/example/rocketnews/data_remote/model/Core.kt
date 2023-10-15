package com.example.rocketnews.data_remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Core(
    @SerialName("core")
    val core: String,
    @SerialName("flight")
    val flight: Int,
    @SerialName("gridfins")
    val gridfins: Boolean,
    @SerialName("landing_attempt")
    val landingAttempt: Boolean,
    @SerialName("landing_success")
    val landingSuccess: Boolean,
    @SerialName("landing_type")
    val landingType: String,
    @SerialName("landpad")
    val landpad: String,
    @SerialName("legs")
    val legs: Boolean,
    @SerialName("reused")
    val reused: Boolean
)