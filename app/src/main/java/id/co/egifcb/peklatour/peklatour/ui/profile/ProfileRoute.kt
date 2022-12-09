package id.co.egifcb.peklatour.peklatour.ui.profile

import androidx.compose.runtime.*
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourAlertDialog
import id.co.egifcb.peklatour.peklatour.ui.profile.model.ProfileEventState
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
        PeklaTourAlertDialog(
            text = "Apakah anda yakin akan keluar ?",
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                openDialog.value = false
                viewModel.onEvent(
                    event = ProfileEventState.Logout
                )
            },
            dismissButton = {
                openDialog.value = false
            }
        )
    }
}