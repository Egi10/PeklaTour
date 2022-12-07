package id.co.egifcb.peklatour.peklatour.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.co.egifcb.peklatour.peklatour.ui.profile.model.ProfileEventState
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    loginOnClick: () -> Unit,
    registerOnClick: () -> Unit,
    logoutOnClick: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val openDialog = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(
        key1 = uiState.value.isLogout,
        block = {
            if (uiState.value.isLogout) {
                logoutOnClick.invoke()
            }
        }
    )

    if (uiState.value.isLogin) {
        ProfileScreen(name = uiState.value.name, email = uiState.value.email, logoutOnClick = {
            openDialog.value = true
        })
    } else {
        ProfileAuthScreen(
            loginOnClick = loginOnClick, registerOnLogin = registerOnClick
        )
    }

    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, buttons = {
            ButtonAlertDialog(
                confirmButton = {
                    openDialog.value = false
                    viewModel.onEvent(
                        event = ProfileEventState.Logout
                    )
                }, dismissButton = {
                    openDialog.value = false
                })
        }, text = {
            Text(
                text = "Apakah anda yakin akan keluar ?",
                style = PeklaTourTheme.typography.caption
            )
        })
    }
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