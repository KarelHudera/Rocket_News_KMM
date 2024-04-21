package karel.hudera.rocketnews.presentation.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchView(
    modifier: Modifier,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onTrailingIconClick: () -> Unit,
    textState: MutableState<TextFieldValue>
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceTint,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceTint
    )

    OutlinedTextField(
        value = textState.value,
        onValueChange = { value ->
            textState.value = value
            onValueChange(value.text)
        },
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            if (textState.value != TextFieldValue("")) {
                IconButton(
                    onClick = onTrailingIconClick
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        colors = textFieldColors,
        singleLine = true
    )
}