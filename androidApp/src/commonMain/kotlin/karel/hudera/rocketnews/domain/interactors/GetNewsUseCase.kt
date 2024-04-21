package karel.hudera.rocketnews.domain.interactors

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.type.BaseUseCase
import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.helpers.NewsDate
import kotlinx.coroutines.CoroutineDispatcher

class GetNewsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<NewsDate, News>(dispatcher){
    override suspend fun block(param: NewsDate): News = repository.getNews(param)
}