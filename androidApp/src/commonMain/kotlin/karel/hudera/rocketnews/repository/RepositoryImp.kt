package karel.hudera.rocketnews.repository

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.helpers.NewsDate
import karel.hudera.rocketnews.helpers.NewsOffset
import karel.hudera.rocketnews.helpers.RocketId
import kotlinx.coroutines.flow.Flow

class RepositoryImp(
    private val cacheData: ICacheData,
    private val remoteData: IRemoteData,
) : IRepository {

    override suspend fun getRockets(): List<Rocket> =
        remoteData.getRocketsFromApi()

    override suspend fun getRocket(id: RocketId): Rocket =
        remoteData.getRocketFromApi(id)

    override suspend fun getNews(date: NewsDate): News =
        remoteData.getNewsFromApi(date)

    override suspend fun getRocketsFavorites(): Flow<List<Rocket>> =
        cacheData.getAllRocketFavorites()

    override suspend fun addRocketToFavorites(rocket: Rocket) =
        cacheData.addRocketToFavorite(rocket)

    override suspend fun removeRocketFromFavorite(idRocket: RocketId) =
        cacheData.removeRocketFromFavorite(idRocket)

    override suspend fun isRocketFavorite(idRocket: RocketId): Boolean =
        cacheData.isRocketFavorite(idRocket)

    override suspend fun getSpaceFlightNews(newsOffset: NewsOffset): List<SpaceFlightNews> =
        remoteData.getSpaceFlightNewsFromApi(newsOffset)

    override suspend fun getSpaceFlightNew(idSpaceFlightNews: String): SpaceFlightNews =
        remoteData.getSpaceFlightNewFromApi(idSpaceFlightNews)
}