package id.co.egifcb.peklatour.peklatour.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun PeklaTourAlertDialog(
    text: String,
    onDismissRequest: () -> Unit,
    confirmButton: () -> Unit,
    dismissButton: () -> Unit
) {
    AlertDialog(onDismissRequest = {
        onDismissRequest.invoke()
    }, buttons = {
        ButtonAlertDialog(
            confirmButton = {
                confirmButton.invoke()
            }, dismissButton = {
                dismissButton.invoke()
            })
    }, text = {
        Text(
            text = text,
            style = PeklaTourTheme.typography.caption
        )
    })
}

@Composable
private fun ButtonAlertDialog(
    confirmButton: () -> Unit, dismissButton: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = 16.dp
            ), horizontalArrangement = Arrangement.End
    ) {
        TextButtonAlertDialog(
            text = "Cancel", onClick = dismissButton
        )

        TextButtonAlertDialog(
            text = "Ok", onClick = confirmButton
        )
    }
}

@Composable
private fun TextButtonAlertDialog(
    text: String, onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(text = text)
    }
}