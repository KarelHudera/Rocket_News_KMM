package com.example.rocketnews.data_remote

import com.example.rocketnews.data_remote.model.ApiCharacter
import com.example.rocketnews.data_remote.model.ApiCharactersResponse
import com.example.rocketnews.data_remote.model.mapper.ApiCharacterMapper
import com.example.rocketnews.domain.model.Character
import com.example.rocketnews.repository.IRemoteData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RemoteDataImp(
    private val endPoint: String,
    private val httpClient: HttpClient,
    private val apiCharacterMapper: ApiCharacterMapper,
) : IRemoteData {
    override suspend fun getCharactersFromApi(): List<Character> =
        apiCharacterMapper.map(
            (httpClient.get("$endPoint/api/character").body<ApiCharactersResponse>()).results
        )

    override suspend fun getCharacterFromApi(id: Int): Character =
        apiCharacterMapper.map(
            httpClient.get("$endPoint/api/character/$id").body<ApiCharacter>()
        )
}