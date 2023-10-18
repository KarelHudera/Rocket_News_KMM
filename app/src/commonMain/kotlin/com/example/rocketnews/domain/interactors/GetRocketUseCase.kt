package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.CoroutineDispatcher

class GetRocketUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Long, Rocket>(dispatcher){
    override suspend fun block(param: Long): Rocket = repository.getRocket(param)
}