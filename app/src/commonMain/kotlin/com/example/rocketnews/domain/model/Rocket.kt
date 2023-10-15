package com.example.rocketnews.domain.model

data class Rocket(
    val patchSmall: String, // image
    val patchLarge: String, // image
    val webcast: String,
    val youtube_id: String,
    val article: String,
    val wikipedia: String,
    val static_fire_date_utc: String,
    val success: Boolean,
    val payloads: List<String>,
    val date_utc: String,
    val name: String,
    val upcoming: Boolean,
    val id: String,
)