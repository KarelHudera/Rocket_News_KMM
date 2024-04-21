package karel.hudera.rocketnews.domain.interactors

import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.type.BaseUseCase
import karel.hudera.rocketnews.helpers.RocketId
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