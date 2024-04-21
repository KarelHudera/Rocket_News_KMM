package karel.hudera.rocketnews.domain.interactors

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.type.BaseUseCase
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.helpers.NewsOffset
import kotlinx.coroutines.CoroutineDispatcher

class GetSpaceFlightNewsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<NewsOffset, List<SpaceFlightNews>>(dispatcher) {
    override suspend fun block(param: NewsOffset): List<SpaceFlightNews> = repository.getSpaceFlightNews(param)
}