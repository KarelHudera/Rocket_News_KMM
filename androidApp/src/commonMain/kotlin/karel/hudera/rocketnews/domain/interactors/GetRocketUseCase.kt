package karel.hudera.rocketnews.domain.interactors

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.type.BaseUseCase
import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.helpers.RocketId
import kotlinx.coroutines.CoroutineDispatcher

class GetRocketUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<RocketId, Rocket>(dispatcher){
    override suspend fun block(param: RocketId): Rocket = repository.getRocket(param)
}