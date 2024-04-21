package karel.hudera.rocketnews.presentation.ui.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import karel.hudera.rocketnews.presentation.ui.common.ProgressIndicator

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        ProgressIndicator(Modifier.align(Alignment.Center))
    }
}