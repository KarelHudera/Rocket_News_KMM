package com.example.rocketnews.data_remote.model.apiRocket

import kotlinx.serialization.Serializable

@Serializable
data class Flickr(
    val original: List<String>,
    //val small: List<Any>
)