package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.CoroutineDispatcher

class GetRocketsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Unit, List<Rocket>>(dispatcher){
    override suspend fun block(param: Unit): List<Rocket> = repository.getRockets()
}