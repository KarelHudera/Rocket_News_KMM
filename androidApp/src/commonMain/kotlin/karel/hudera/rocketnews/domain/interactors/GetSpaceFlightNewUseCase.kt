package karel.hudera.rocketnews.domain.interactors

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.type.BaseUseCase
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import kotlinx.coroutines.CoroutineDispatcher

class GetSpaceFlightNewUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<String, SpaceFlightNews>(dispatcher){
    override suspend fun block(param: String): SpaceFlightNews = repository.getSpaceFlightNew(param)
}