package karel.hudera.rocketnews.data_remote

import karel.hudera.rocketnews.data_remote.model.apiNews.ApiNews
import karel.hudera.rocketnews.data_remote.model.apiRocket.ApiRocket
import karel.hudera.rocketnews.data_remote.model.apiSpaceFlight.ApiSpaceFlightNews
import karel.hudera.rocketnews.data_remote.model.apiSpaceFlight.Result
import karel.hudera.rocketnews.data_remote.model.mapper.ApiNewsMapper
import karel.hudera.rocketnews.data_remote.model.mapper.ApiRocketMapper
import karel.hudera.rocketnews.data_remote.model.mapper.ApiSpaceFlightMapper
import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.helpers.NewsDate
import karel.hudera.rocketnews.helpers.NewsOffset
import karel.hudera.rocketnews.helpers.RocketId
import karel.hudera.rocketnews.repository.IRemoteData
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
            httpClient.get("$SPACEFLIGHT_NEWS_URL$SPACEFLIGHT_NEWS_ARTICLES") {
                parameter("format", "json")
                parameter("limit", "50")
                parameter("offset", newsOffset)
            }.body<ApiSpaceFlightNews>().results
        )

    override suspend fun getSpaceFlightNewFromApi(idSpaceFlightNews: String): SpaceFlightNews =
        apiSpaceFlightMapper.map(
            httpClient.get("$SPACEFLIGHT_NEWS_URL$SPACEFLIGHT_NEWS_ARTICLES$idSpaceFlightNews") {
                parameter("format", "json")
            }.body<Result>()
        )
}