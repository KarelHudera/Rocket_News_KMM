package com.example.rocketnews.data_remote

import com.example.rocketnews.domain.model.News
import com.example.rocketnews.domain.model.Rocket


interface IRemoteData {
    suspend fun getRocketsFromApi(): List<Rocket>
    suspend fun getRocketFromApi(id: String): Rocket
    suspend fun getNewsFromApi(): News
}