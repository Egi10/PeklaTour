package id.co.egifcb.peklatour.peklatour.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourAlertDialog
import id.co.egifcb.peklatour.peklatour.ui.profile.model.ProfileEventState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    loginOnClick: () -> Unit,
    registerOnClick: () -> Unit,
    logoutOnClick: () -> Unit,
    developerInfoOnClick: () -> Unit,
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.value.isLogin) {
            ProfileScreen(
                name = uiState.value.name,
                email = uiState.value.email,
                logoutOnClick = {
                    openDialog.value = true
                },
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
        } else {
            ProfileAuthScreen(
                loginOnClick = loginOnClick,
                registerOnLogin = registerOnClick,
                modifier = Modifier
                    .weight(1f)
            )
        }

        TextButton(
            onClick = {
                developerInfoOnClick.invoke()
            },
            modifier = Modifier
                .semantics {
                    contentDescription = "about_page"
                }
        ) {
            Text(text = stringResource(R.string.developer_info))
        }

        Spacer(
            modifier = Modifier
                .padding(
                    bottom = 16.dp
                )
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