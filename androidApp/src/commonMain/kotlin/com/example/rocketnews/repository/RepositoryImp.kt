package com.example.rocketnews.repository

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.model.News
import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

class RepositoryImp(
    private val cacheData: ICacheData,
    private val remoteData: IRemoteData,
) : IRepository {

    override suspend fun getRockets(): List<Rocket> =
        remoteData.getRocketsFromApi()

    override suspend fun getRocket(id: String): Rocket =
        remoteData.getRocketFromApi(id)

    override suspend fun getNews(): News =
        remoteData.getNewsFromApi()

    override suspend fun getRocketsFavorites(): Flow<List<Rocket>> =
        cacheData.getAllRocketFavorites()

    override suspend fun addRocketToFavorites(rocket: Rocket) =
        cacheData.addRocketToFavorite(rocket)

    override suspend fun removeRocketFromFavorite(idRocket: String) =
        cacheData.removeRocketFromFavorite(idRocket)

    override suspend fun isRocketFavorite(idRocket: String): Boolean =
        cacheData.isRocketFavorite(idRocket)
}