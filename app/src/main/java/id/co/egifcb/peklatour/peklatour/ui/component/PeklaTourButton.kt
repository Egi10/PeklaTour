package id.co.egifcb.peklatour.peklatour.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun PeklaTourButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box {
            if (loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                )
            } else {
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 14.sp,
                    style = PeklaTourTheme.typography.body
                )
            }
        }
    }
}

@Preview
@Composable
private fun PeklaTourButtonPreview() {
    PeklaTourTheme {
        Column {
            PeklaTourButton(
                text = "Masuk",
                onClick = { }
            )

            PeklaTourButton(
                text = "Masuk",
                onClick = { },
                loading = true
            )
        }
    }
}