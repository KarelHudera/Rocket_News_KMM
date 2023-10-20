package com.example.rocketnews.data_cache

import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

interface ICacheData {
    suspend fun addRocketToFavorite(rocket: Rocket)
    suspend fun removeRocketFromFavorite(idRocket: String)
    suspend fun getAllRocketFavorites(): Flow<List<Rocket>>
    suspend fun isRocketFavorite(idRocket: String): Boolean
}