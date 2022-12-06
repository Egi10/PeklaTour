package id.co.egifcb.peklatour.peklatour.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun PeklaTourTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
) {
    var visibilityPassword by remember {
        mutableStateOf(isPassword)
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
                style = PeklaTourTheme.typography.caption
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        trailingIcon = {
            if (isPassword) {
                val icon = if (!visibilityPassword) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                IconButton(
                    onClick = {
                        visibilityPassword = !visibilityPassword
                    }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            }
        },
        visualTransformation = if (!visibilityPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        textStyle = PeklaTourTheme.typography.caption
    )
}

@Preview(
    showBackground = true
)
@Composable
fun PeklaTourTextFieldPreview() {
    PeklaTourTheme {
        PeklaTourTextField(
            value = "Leo",
            onValueChange = {},
            label = "Nama",
            isPassword = true
        )
    }
}