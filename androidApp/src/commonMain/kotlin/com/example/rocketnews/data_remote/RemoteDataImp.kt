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

    override suspend fun getRocketsFromApi(): List<Rocket> =
        apiRocketMapper.map(
            httpClient.get("$SPACEX_URL$SPACEX_LAUNCHES").body<List<ApiRocket>>()
        )

    override suspend fun getRocketFromApi(id: RocketId): Rocket =
        apiRocketMapper.map(
            httpClient.get("$SPACEX_URL$SPACEX_LAUNCHES$id").body<ApiRocket>()
        )

    override suspend fun getNewsFromApi(date: NewsDate): News =
        apiNewsMapper.map(
            httpClient.get("$NASA_URL$NASA_APOD") {
                header("X-Api-Key", API_KEY)
                parameter("date", date)
            }.body<ApiNews>()
        )

    override suspend fun getSpaceFlightNewsFromApi(newsOffset: NewsOffset): List<SpaceFlightNews> =
        apiSpaceFlightMapper.map(
            (httpClient.get("$SPACEFLIGHT_NEWS_URL$SPACEFLIGHT_NEWS_ARTICLES?format=json&limit=50&offset=$newsOffset")
                .body<ApiSpaceFlightNews>()).results
        )

    override suspend fun getSpaceFlightNewFromApi(idSpaceFlightNews: String): SpaceFlightNews =
        apiSpaceFlightMapper.map(
            httpClient.get("$SPACEFLIGHT_NEWS_URL$SPACEFLIGHT_NEWS_ARTICLES$idSpaceFlightNews") {
                parameter("format", "json")
            }.body<Result>()
        )
}