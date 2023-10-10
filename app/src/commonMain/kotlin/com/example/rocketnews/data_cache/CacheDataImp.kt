package com.example.rocketnews.data_cache

import app.cash.sqldelight.coroutines.asFlow
import com.example.rocketnews.data_cache.sqldelight.SharedDatabase
import com.example.rocketnews.domain.model.Character
import com.example.rocketnews.domain.model.Gender
import com.example.rocketnews.domain.model.Status
import com.example.rocketnews.repository.ICacheData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CacheDataImp(
    private val sharedDatabase: SharedDatabase,
) : ICacheData {

    override suspend fun addCharacterToFavorite(character: Character) {
        sharedDatabase {
            it.appDatabaseQueries.insertCharacterFavorite(
                character.id.toLong(),
                character.name,
                character.status,
                character.species,
                character.gender,
                character.origin,
                character.location,
                character.image
            )
        }
    }

    override suspend fun removeCharacterFromFavorite(idCharacter: Int) {
        sharedDatabase {
            it.appDatabaseQueries.removeCharacterFavorite(idCharacter.toLong())
        }
    }

    override suspend fun getAllCharacterFavorites(): Flow<List<Character>> =
        sharedDatabase { appDatabase ->
            appDatabase.appDatabaseQueries.selectAllCharacterFavorite(::mapFavorite).asFlow()
                .map { it.executeAsList() }
        }

    override suspend fun isCharacterFavorite(idCharacter: Int): Boolean =
        sharedDatabase {
            it.appDatabaseQueries.selectCharacterFavoriteById(idCharacter.toLong()).executeAsOne()
        }

    private fun mapFavorite(
        id: Long,
        name: String,
        status: Status,
        species: String,
        gender: Gender,
        origin: String,
        location: String,
        image: String,
    ): Character = Character(id.toInt(), name, status, species, gender, origin, location, image)
}