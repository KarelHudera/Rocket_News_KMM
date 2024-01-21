package com.example.rocketnews.domain

import com.example.rocketnews.domain.model.News
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.NewsDate
import com.example.rocketnews.helpers.NewsOffset
import com.example.rocketnews.helpers.RocketId
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getRockets(): List<Rocket>
    suspend fun getRocketsFavorites(): Flow<List<Rocket>>
    suspend fun getRocket(id: RocketId): Rocket
    suspend fun addRocketToFavorites(rocket: Rocket)
    suspend fun removeRocketFromFavorite(idRocket: RocketId)
    suspend fun isRocketFavorite(idRocket: RocketId): Boolean
    suspend fun getNews(date: NewsDate): News
    suspend fun getSpaceFlightNews(newsOffset: NewsOffset): List<SpaceFlightNews>
    suspend fun getSpaceFlightNew(idSpaceFlightNews: String): SpaceFlightNews
}