package com.example.rocketnews.domain.model

data class News (
    val date: String,
    val explanation: String,
    val hdurl: String, // image
    val title: String,
    val url: String // image
)