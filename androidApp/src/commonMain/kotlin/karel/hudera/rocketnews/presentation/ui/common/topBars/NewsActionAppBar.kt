package karel.hudera.rocketnews.presentation.ui.common.topBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import karel.hudera.rocketnews.presentation.ui.common.ActionBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsActionAppBar(
    title: String,
    onClickDatePicker: () -> Unit,
    isShadowEnabled: Boolean = false
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            ActionBarIcon(
                onClick = onClickDatePicker,
                icon = Icons.Filled.DateRange
            )
        },
        modifier = if (isShadowEnabled) {
            Modifier.shadow(2.dp)
        } else {
            Modifier
        }
    )
}