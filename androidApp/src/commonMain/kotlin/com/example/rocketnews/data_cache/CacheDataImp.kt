package com.example.rocketnews.data_cache

import app.cash.sqldelight.coroutines.asFlow
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.data_cache.sqldelight.SharedDatabase
import com.example.rocketnews.repository.ICacheData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CacheDataImp(
    private val sharedDatabase: SharedDatabase,
) : ICacheData {

    override suspend fun addRocketToFavorite(rocket: Rocket) {
        sharedDatabase {
            it.appDatabaseQueries.insertRocketFavorite(
                rocket.patchSmall, // image
                rocket.patchLarge, // image
                rocket.reddit,
                rocket.flickr,
                rocket.webcast,
                rocket.youtube_id,
                rocket.article,
                rocket.wikipedia,
                rocket.static_fire_date_utc,
                rocket.success,
                rocket.details,
                rocket.date_utc,
                rocket.name,
                rocket.upcoming,
                rocket.id
            )
        }
    }

    override suspend fun removeRocketFromFavorite(idRocket: String) {
        sharedDatabase {
            it.appDatabaseQueries.removeRocketFavorite(idRocket)
        }
    }

    override suspend fun getAllRocketFavorites(): Flow<List<Rocket>> =
        sharedDatabase { appDatabase ->
            appDatabase.appDatabaseQueries.selectAllRocketFavorite(::mapFavorite).asFlow()
                .map { it.executeAsList() }
        }

    override suspend fun isRocketFavorite(idRocket: String): Boolean =
        sharedDatabase {
            it.appDatabaseQueries.selectRocketFavoriteById(idRocket).executeAsOne()
        }

    private fun mapFavorite(
        patchSmall: String,
        patchLarge: String,
        reddit: String,
        filckr: List<String>,
        webcast: String,
        youtube_id: String,
        article: String,
        wikipedia: String,
        static_fire_date_utc: String,
        success: Boolean,
        details: String,
        date_utc: String,
        name: String,
        upcoming: Boolean,
        id: String,
    ): Rocket = Rocket(
        patchSmall,
        patchLarge,
        reddit,
        filckr,
        webcast,
        youtube_id,
        article,
        wikipedia,
        static_fire_date_utc,
        success,
        details,
        date_utc,
        name,
        upcoming,
        id,
    )
}