package com.example.rocketnews.data_remote

import com.example.rocketnews.data_remote.model.ApiRocket
import com.example.rocketnews.data_remote.model.mapper.ApiRocketMapper
import com.example.rocketnews.domain.model.Rocket
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RemoteDataImp(
    private val endPoint: String,
    private val httpClient: HttpClient,
    private val apiRocketMapper: ApiRocketMapper,
) : IRemoteData {
    override suspend fun getRocketsFromApi(): List<Rocket> =
        apiRocketMapper.map(
            httpClient.get(endPoint).body<List<ApiRocket>>()
        )
    override suspend fun getRocketFromApi(id: String): Rocket =
        apiRocketMapper.map(
            httpClient.get("$endPoint$id").body<ApiRocket>()
        )
}