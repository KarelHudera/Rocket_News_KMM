package com.example.rocketnews.data_remote

import com.example.rocketnews.data_remote.model.apiNews.ApiNews
import com.example.rocketnews.data_remote.model.apiRocket.ApiRocket
import com.example.rocketnews.data_remote.model.apiSpaceFlight.ApiSpaceFlightNews
import com.example.rocketnews.data_remote.model.apiSpaceFlight.Result
import com.example.rocketnews.data_remote.model.mapper.ApiNewsMapper
import com.example.rocketnews.data_remote.model.mapper.ApiRocketMapper
import com.example.rocketnews.data_remote.model.mapper.ApiSpaceFlightMapper
import com.example.rocketnews.domain.model.News
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.NewsDate
import com.example.rocketnews.helpers.NewsOffset
import com.example.rocketnews.helpers.RocketId
import com.example.rocketnews.repository.IRemoteData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RemoteDataImp(
    private val httpClient: HttpClient,
    private val apiRocketMapper: ApiRocketMapper,
    private val apiNewsMapper: ApiNewsMapper,
    private val apiSpaceFlightMapper: ApiSpaceFlightMapper
) : IRemoteData {
    private val endPointSpaceFlight = "https://api.spaceflightnewsapi.net/v4/articles/"
    private val endPointSpaceX = "https://api.spacexdata.com/v5/launches/"
    private val endPointNASA = "https://api.nasa.gov/planetary/apod"
    private val apiKey = "428D3IouMHvoxKD8eaefoZKwe0w2Syv3t5eFbklA"

    override suspend fun getRocketsFromApi(): List<Rocket> =
        apiRocketMapper.map(
            httpClient.get(endPointSpaceX).body<List<ApiRocket>>()
        )
    override suspend fun getRocketFromApi(id: RocketId): Rocket =
        apiRocketMapper.map(
            httpClient.get("$endPointSpaceX$id").body<ApiRocket>()
        )
    override suspend fun getNewsFromApi(date: NewsDate): News =
        apiNewsMapper.map(
            httpClient.get("$endPointNASA?api_key=$apiKey&date=$date"){
                header("X-Api-Key", apiKey)
            }.body<ApiNews>()
    )
    override suspend fun getSpaceFlightNewsFromApi(newsOffset: NewsOffset): List<SpaceFlightNews> =
        apiSpaceFlightMapper.map(
            (httpClient.get("$endPointSpaceFlight?format=json&limit=50&offset=$newsOffset").body<ApiSpaceFlightNews>()).results
        )

    override suspend fun getSpaceFlightNewFromApi(idSpaceFlightNews: String): SpaceFlightNews =
        apiSpaceFlightMapper.map(
            httpClient.get("$endPointSpaceFlight$idSpaceFlightNews/?format=json").body<Result>()
        )
}