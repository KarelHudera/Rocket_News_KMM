package com.example.rocketnews.data_remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Flickr(
    val original: List<String>,
    //val small: List<Any>
)