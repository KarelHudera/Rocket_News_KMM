package com.example.rocketnews.domain

import com.example.rocketnews.domain.model.News
import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getRockets(): List<Rocket>
    suspend fun getRocketsFavorites(): Flow<List<Rocket>>
    suspend fun getRocket(id: String): Rocket
    suspend fun addRocketToFavorites(character: Rocket)
    suspend fun removeRocketFromFavorite(idRocket: String)
    suspend fun isRocketFavorite(idRocket: String): Boolean
    suspend fun getNews(): News

}