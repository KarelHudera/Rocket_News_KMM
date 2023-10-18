package com.example.rocketnews.data_remote

import com.example.rocketnews.data_remote.model.ApiRocket
import com.example.rocketnews.data_remote.model.mapper.ApiRocketMapper
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.data_remote.model.ApiRocketsResponse
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
            (httpClient.get(endPoint).body<ApiRocketsResponse>()).results
        )
    override suspend fun getRocketFromApi(id: Long): Rocket =
        apiRocketMapper.map(
            httpClient.get("$endPoint$id").body<ApiRocket>()
        )
}