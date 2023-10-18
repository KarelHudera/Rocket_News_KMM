package com.example.rocketnews.domain

import com.example.rocketnews.data_cache.ICacheData
import com.example.rocketnews.data_remote.IRemoteData
import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

class RepositoryImp(
    private val cacheData: ICacheData,
    private val remoteData: IRemoteData,
) : IRepository {

    override suspend fun getRockets(): List<Rocket> =
        remoteData.getRocketsFromApi()

    override suspend fun getRocketsFavorites(): Flow<List<Rocket>> =
        cacheData.getAllRocketFavorites()

    override suspend fun getRocket(id: Long): Rocket =
        remoteData.getRocketFromApi(id)

    override suspend fun addRocketToFavorites(character: Rocket) =
        cacheData.addRocketToFavorite(character)

    override suspend fun removeRocketFromFavorite(idRocket: Long) =
        cacheData.removeRocketFromFavorite(idRocket)

    override suspend fun isRocketFavorite(idRocket: Long): Boolean =
        cacheData.isRocketFavorite(idRocket)
}