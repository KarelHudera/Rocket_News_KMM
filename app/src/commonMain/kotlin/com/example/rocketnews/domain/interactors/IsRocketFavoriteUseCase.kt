package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class IsRocketFavoriteUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<String, Boolean>(dispatcher) {
    override suspend fun block(param: String): Boolean = repository.isRocketFavorite(param)
}