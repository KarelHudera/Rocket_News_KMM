package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.helpers.RocketId
import kotlinx.coroutines.CoroutineDispatcher

class IsRocketFavoriteUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<RocketId, Boolean>(dispatcher) {
    override suspend fun block(param: RocketId): Boolean = repository.isRocketFavorite(param)
}