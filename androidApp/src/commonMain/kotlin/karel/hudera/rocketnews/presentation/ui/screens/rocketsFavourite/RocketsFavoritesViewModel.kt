package karel.hudera.rocketnews.presentation.ui.screens.rocketsFavourite

import cafe.adriel.voyager.core.model.screenModelScope
import karel.hudera.rocketnews.domain.interactors.GetRocketsFavoritesUseCase
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class RocketsFavoritesViewModel(
    private val getRocketsFavoritesUseCase: GetRocketsFavoritesUseCase
) : BaseViewModel<RocketsFavoritesContract.Event, RocketsFavoritesContract.State, RocketsFavoritesContract.Effect>() {

    init {
        getRocketsFavorites()
    }

    override fun createInitialState(): RocketsFavoritesContract.State =
        RocketsFavoritesContract.State(
            rocketsFavorites = ResourceUiState.Idle
        )

    override fun handleEvent(event: RocketsFavoritesContract.Event) {
        when (event) {
            RocketsFavoritesContract.Event.OnTryCheckAgainClick -> getRocketsFavorites()
            is RocketsFavoritesContract.Event.OnRocketClick ->
                setEffect { RocketsFavoritesContract.Effect.NavigateToDetailRocket(event.idRocket) }

            RocketsFavoritesContract.Event.OnBackPressed ->
                setEffect { RocketsFavoritesContract.Effect.BackNavigation }
        }
    }

    private fun getRocketsFavorites() {
        setState { copy(rocketsFavorites = ResourceUiState.Loading) }
        screenModelScope.launch {
            getRocketsFavoritesUseCase(Unit).collect {
                it.onSuccess {
                    setState {
                        copy(
                            rocketsFavorites =
                            if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                }.onFailure { setState { copy(rocketsFavorites = ResourceUiState.Error()) } }
            }
        }
    }
}