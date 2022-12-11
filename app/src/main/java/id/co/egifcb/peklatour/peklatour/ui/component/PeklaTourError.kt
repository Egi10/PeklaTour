package id.co.egifcb.peklatour.peklatour.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun PeklaTourError(
    message: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Oops!",
            style = PeklaTourTheme.typography.title,
            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 8.dp
                )
        )

        Text(
            text = message,
            style = PeklaTourTheme.typography.caption
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
        )

        OutlinedButton(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Unspecified
            ),
            content = {
                Text(
                    text = stringResource(R.string.retry),
                    style = PeklaTourTheme.typography.body
                )
            }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PeklaTourErrorPreview() {
    PeklaTourTheme {
        PeklaTourError(
            message = "Error Message",
            onClick = {}
        )
    }
}