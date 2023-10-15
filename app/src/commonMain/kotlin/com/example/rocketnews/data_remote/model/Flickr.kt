package com.example.rocketnews.data_remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flickr(
    @SerialName("original")
    val original: List<String>,

    // generated

//    @SerialName("small")
//    val small: List<Any>
)