package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.helpers.RocketId
import kotlinx.coroutines.CoroutineDispatcher

class SwitchRocketFavoriteUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<RocketId, Boolean>(dispatcher) {
    override suspend fun block(param: RocketId): Boolean {
        if (repository.isRocketFavorite(param)) {
            repository.removeRocketFromFavorite(param)
        } else {
            repository.addRocketToFavorites(repository.getRocket(param))
        }
        return repository.isRocketFavorite(param)
    }
}