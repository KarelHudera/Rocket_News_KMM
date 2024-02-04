package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.domain.model.SpaceFlightNews
import com.example.rocketnews.helpers.NewsOffset
import kotlinx.coroutines.CoroutineDispatcher

class GetSpaceFlightNewsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<NewsOffset, List<SpaceFlightNews>>(dispatcher) {
    override suspend fun block(param: NewsOffset): List<SpaceFlightNews> = repository.getSpaceFlightNews(param)
}