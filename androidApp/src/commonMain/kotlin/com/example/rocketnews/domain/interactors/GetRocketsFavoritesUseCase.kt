package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCaseFlow
import com.example.rocketnews.domain.model.Rocket
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetRocketsFavoritesUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<Unit, List<Rocket>>(dispatcher) {
    override suspend fun build(param: Unit): Flow<List<Rocket>> = repository.getRocketsFavorites()
}