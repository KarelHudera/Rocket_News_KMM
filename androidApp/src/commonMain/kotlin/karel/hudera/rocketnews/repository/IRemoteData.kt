package karel.hudera.rocketnews.repository

import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.helpers.NewsDate
import karel.hudera.rocketnews.helpers.NewsOffset
import karel.hudera.rocketnews.helpers.RocketId


interface IRemoteData {
    suspend fun getRocketsFromApi(): List<Rocket>
    suspend fun getRocketFromApi(id: RocketId): Rocket
    suspend fun getNewsFromApi(date: NewsDate): News
    suspend fun getSpaceFlightNewsFromApi(newsOffset: NewsOffset): List<SpaceFlightNews>
    suspend fun getSpaceFlightNewFromApi(idSpaceFlightNews: String): SpaceFlightNews
}