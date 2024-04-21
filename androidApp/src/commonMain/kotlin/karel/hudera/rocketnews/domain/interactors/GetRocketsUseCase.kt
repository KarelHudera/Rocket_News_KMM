package karel.hudera.rocketnews.domain.interactors

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.type.BaseUseCase
import karel.hudera.rocketnews.domain.model.Rocket
import kotlinx.coroutines.CoroutineDispatcher

class GetRocketsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Unit, List<Rocket>>(dispatcher){
    override suspend fun block(param: Unit): List<Rocket> = repository.getRockets()
}