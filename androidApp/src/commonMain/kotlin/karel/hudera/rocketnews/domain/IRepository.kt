package karel.hudera.rocketnews.domain

import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.helpers.NewsDate
import karel.hudera.rocketnews.helpers.NewsOffset
import karel.hudera.rocketnews.helpers.RocketId
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