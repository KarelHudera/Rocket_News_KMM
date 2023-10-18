package com.example.rocketnews.domain

import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getRockets(): List<Rocket>
    suspend fun getRocketsFavorites(): Flow<List<Rocket>>
    suspend fun getRocket(id: Long): Rocket
    suspend fun addRocketToFavorites(character: Rocket)
    suspend fun removeRocketFromFavorite(idRocket: Long)
    suspend fun isRocketFavorite(idRocket: Long): Boolean
}