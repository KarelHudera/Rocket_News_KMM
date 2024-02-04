package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.domain.model.SpaceFlightNews
import kotlinx.coroutines.CoroutineDispatcher

class GetSpaceFlightNewUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<String, SpaceFlightNews>(dispatcher){
    override suspend fun block(param: String): SpaceFlightNews = repository.getSpaceFlightNew(param)
}