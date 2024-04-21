package karel.hudera.rocketnews.presentation.ui.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import karel.hudera.rocketnews.presentation.ui.common.Space

@Composable
fun Empty(
    modifier: Modifier = Modifier, msg: String, onCheckAgain: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = msg, style = MaterialTheme.typography.headlineSmall
            )
            Space()
            OutlinedButton(
                onClick = onCheckAgain
            ) {
                Text(
                    text = "Check Again",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}