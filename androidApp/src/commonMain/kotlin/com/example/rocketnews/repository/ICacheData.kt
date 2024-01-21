package com.example.rocketnews.repository

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.helpers.RocketId
import kotlinx.coroutines.flow.Flow

interface ICacheData {
    suspend fun addRocketToFavorite(rocket: Rocket)
    suspend fun removeRocketFromFavorite(idRocket: String)
    suspend fun getAllRocketFavorites(): Flow<List<Rocket>>
    suspend fun isRocketFavorite(idRocket: RocketId): Boolean
}