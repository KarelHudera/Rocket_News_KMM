package com.example.rocketnews.data_remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiRocket(
    @SerialName("auto_update")
    val autoUpdate: Boolean,
    @SerialName("capsules")
    val capsules: List<String>,
    @SerialName("cores")
    val cores: List<Core>,
    @SerialName("crew")
    val crew: List<Crew>,
    @SerialName("date_local")
    val dateLocal: String,
    @SerialName("date_precision")
    val datePrecision: String,
    @SerialName("date_unix")
    val dateUnix: Int,
    @SerialName("date_utc")
    val dateUtc: String,
    @SerialName("details")
    val details: String,
    @SerialName("failures")
    val failures: List<Failure>,
    @SerialName("fairings")
    val fairings: Fairings,
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("id")
    val id: String,
    @SerialName("launch_library_id")
    val launchLibraryId: String,
    @SerialName("launchpad")
    val launchpad: String,
    @SerialName("links")
    val links: Links,
    @SerialName("name")
    val name: String,
    @SerialName("net")
    val net: Boolean,
    @SerialName("payloads")
    val payloads: List<String>,
    @SerialName("rocket")
    val rocket: String,
    @SerialName("ships")
    val ships: List<String>,
    @SerialName("static_fire_date_unix")
    val staticFireDateUnix: Int,
    @SerialName("static_fire_date_utc")
    val staticFireDateUtc: String,
    @SerialName("success")
    val success: Boolean,
    @SerialName("tbd")
    val tbd: Boolean,
    @SerialName("upcoming")
    val upcoming: Boolean,
    @SerialName("window")
    val window: Int
)