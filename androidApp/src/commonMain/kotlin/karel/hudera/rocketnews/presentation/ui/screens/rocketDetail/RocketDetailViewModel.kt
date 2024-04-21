package karel.hudera.rocketnews.presentation.ui.screens.rocketDetail

import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import karel.hudera.rocketnews.domain.interactors.GetRocketUseCase
import karel.hudera.rocketnews.domain.interactors.IsRocketFavoriteUseCase
import karel.hudera.rocketnews.domain.interactors.SwitchRocketFavoriteUseCase
import karel.hudera.rocketnews.helpers.RocketId
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketDetailViewModel(
    private val getRocketUseCase: GetRocketUseCase,
    private val isRocketFavoriteUseCase: IsRocketFavoriteUseCase,
    private val switchRocketFavoriteUseCase: SwitchRocketFavoriteUseCase,
    private val rocketId: RocketId,
) : BaseViewModel<RocketDetailContract.Event, RocketDetailContract.State, RocketDetailContract.Effect>() {

    init {
        getRocket(rocketId)
        checkIfIsFavorite(rocketId)
    }

    override fun createInitialState(): RocketDetailContract.State =
        RocketDetailContract.State(
            rocket = ResourceUiState.Idle,
            isFavorite = ResourceUiState.Idle,
        )

    override fun handleEvent(event: RocketDetailContract.Event) {
        when (event) {
            is RocketDetailContract.Event.OnFavoriteClick -> switchRocketFavorite(rocketId)
            is RocketDetailContract.Event.OnTryCheckAgainClick -> getRocket(rocketId)
            is RocketDetailContract.Event.OnEmptyUrlClick -> setEffect { RocketDetailContract.Effect.EmptyUrl }
            is RocketDetailContract.Event.OnBackPressed -> setEffect { RocketDetailContract.Effect.BackNavigation }
        }
    }

    private fun getRocket(rocketId: RocketId) {
        setState { copy(rocket = ResourceUiState.Loading) }
        screenModelScope.launch {
            getRocketUseCase(rocketId)
                .onSuccess {
                    setState { copy(rocket = ResourceUiState.Success(it)) }
                    Logger.i { "$it SUCCESS" }
                }
                .onFailure {
                    setState { copy(rocket = ResourceUiState.Error()) }
                    Logger.i { "$it FAILURE" }
                }
        }
    }

    private fun checkIfIsFavorite(idRocket: RocketId) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        screenModelScope.launch {
            isRocketFavoriteUseCase(idRocket)
                .onSuccess { setState { copy(isFavorite = ResourceUiState.Success(it)) } }
                .onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }

    private fun switchRocketFavorite(idRocket: RocketId) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        screenModelScope.launch {
            switchRocketFavoriteUseCase(idRocket)
                .onSuccess {
                    setState { copy(isFavorite = ResourceUiState.Success(it)) }
                    if (it) {
                        setEffect { RocketDetailContract.Effect.RocketAdded }
                    } else {
                        setEffect { RocketDetailContract.Effect.RocketRemoved }
                    }

                }.onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }
}