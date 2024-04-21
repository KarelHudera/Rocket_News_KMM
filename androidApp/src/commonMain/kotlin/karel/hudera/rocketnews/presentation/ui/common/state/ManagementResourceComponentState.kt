package karel.hudera.rocketnews.presentation.ui.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import karel.hudera.rocketnews.presentation.model.ResourceUiState
import karel.hudera.rocketnews.presentation.ui.common.Space

@Composable
fun <T> ManagementResourceComponentState(
    modifier: Modifier = Modifier,
    resourceUiState: ResourceUiState<T>,
    successView: @Composable (data: T) -> Unit,
    loadingView: @Composable () -> Unit = { Loading() },
    emptyView: @Composable () -> Unit = { Space() },
    errorView: @Composable () -> Unit = { Space() },
    idleView: @Composable () -> Unit = { Space() },
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        when (resourceUiState) {
            is ResourceUiState.Empty -> emptyView()
            is ResourceUiState.Error -> errorView()
            is ResourceUiState.Loading -> loadingView()
            is ResourceUiState.Success -> successView(resourceUiState.data)
            is ResourceUiState.Idle -> idleView()
        }
    }
}