package com.example.rocketnews.data_remote

import com.example.rocketnews.data_remote.model.apiNews.ApiNews
import com.example.rocketnews.data_remote.model.apiRocket.ApiRocket
import com.example.rocketnews.data_remote.model.mapper.ApiNewsMapper
import com.example.rocketnews.data_remote.model.mapper.ApiRocketMapper
import com.example.rocketnews.domain.model.News
import com.example.rocketnews.domain.model.Rocket
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RemoteDataImp(private val httpClient: HttpClient,
    private val apiRocketMapper: ApiRocketMapper,
    private val apiNewsMapper: ApiNewsMapper
) : IRemoteData {

    private val endPointSpaceX = "https://api.spacexdata.com/v5/launches/"
    private val endPointNASA = "https://api.nasa.gov/planetary/apod"
    private val apiKey = "428D3IouMHvoxKD8eaefoZKwe0w2Syv3t5eFbklA"

    override suspend fun getRocketsFromApi(): List<Rocket> =
        apiRocketMapper.map(
            httpClient.get(endPointSpaceX).body<List<ApiRocket>>()
        )
    override suspend fun getRocketFromApi(id: String): Rocket =
        apiRocketMapper.map(
            httpClient.get("$endPointSpaceX$id").body<ApiRocket>()
        )
    override suspend fun getNewsFromApi(): News =
        apiNewsMapper.map(
            httpClient.get(endPointNASA){
                header("X-Api-Key", apiKey)
            }.body<ApiNews>()
    )
}