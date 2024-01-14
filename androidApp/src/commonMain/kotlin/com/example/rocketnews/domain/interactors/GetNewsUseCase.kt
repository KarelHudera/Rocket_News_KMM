package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.domain.model.News
import kotlinx.coroutines.CoroutineDispatcher

class GetNewsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Unit, News>(dispatcher){
    override suspend fun block(param: Unit): News = repository.getNews()
}