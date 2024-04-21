package karel.hudera.rocketnews.domain.interactors

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.type.BaseUseCaseFlow
import karel.hudera.rocketnews.domain.model.Rocket
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetRocketsFavoritesUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<Unit, List<Rocket>>(dispatcher) {
    override suspend fun build(param: Unit): Flow<List<Rocket>> = repository.getRocketsFavorites()
}