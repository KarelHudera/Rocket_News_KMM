package com.example.rocketnews.domain

import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getCharacters(): List<Rocket>
    suspend fun getCharactersFavorites(): Flow<List<Rocket>>
    suspend fun addCharacterToFavorites(character: Rocket)
    suspend fun removeCharacterFromFavorite(idCharacter: String) // tohle bude string
    suspend fun isCharacterFavorite(idCharacter: String): Boolean // tohe taky
}