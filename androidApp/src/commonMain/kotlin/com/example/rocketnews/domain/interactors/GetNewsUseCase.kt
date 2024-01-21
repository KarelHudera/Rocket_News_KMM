package com.example.rocketnews.domain.interactors

import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.interactors.type.BaseUseCase
import com.example.rocketnews.domain.model.News
import com.example.rocketnews.helpers.NewsDate
import kotlinx.coroutines.CoroutineDispatcher

class GetNewsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<NewsDate, News>(dispatcher){
    override suspend fun block(param: NewsDate): News = repository.getNews(param)
}