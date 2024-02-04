package com.example.rocketnews.domain.model

data class SpaceFlightNews(
    val id: Int,
    val image_url: String,
    val news_site: String,
    val published_at: String,
    val summary: String,
    val title: String,
    val updated_at: String,
    val url: String
)