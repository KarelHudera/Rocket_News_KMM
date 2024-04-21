package karel.hudera.rocketnews.presentation.ui.common.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import karel.hudera.rocketnews.openUrl
import karel.hudera.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailContract
import karel.hudera.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel

@Composable
fun UrlButton(url: String, rocketDetailViewModel: RocketDetailViewModel, icon: Painter) {
    IconButton(
        onClick = {
            if (url != "") {
                openUrl(url)
            } else {
                rocketDetailViewModel.setEvent(
                    RocketDetailContract.Event.OnEmptyUrlClick,
                )
            }
        },
    ) {
        Icon(
            painter = icon,
            modifier = Modifier.size(36.dp),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}