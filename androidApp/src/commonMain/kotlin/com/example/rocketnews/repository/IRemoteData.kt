package com.example.rocketnews.repository

import com.example.rocketnews.domain.model.News
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.NewsDate
import com.example.rocketnews.helpers.NewsOffset
import com.example.rocketnews.helpers.RocketId


interface IRemoteData {
    suspend fun getRocketsFromApi(): List<Rocket>
    suspend fun getRocketFromApi(id: RocketId): Rocket
    suspend fun getNewsFromApi(date: NewsDate): News
    suspend fun getSpaceFlightNewsFromApi(newsOffset: NewsOffset): List<SpaceFlightNews>
    suspend fun getSpaceFlightNewFromApi(idSpaceFlightNews: String): SpaceFlightNews
}