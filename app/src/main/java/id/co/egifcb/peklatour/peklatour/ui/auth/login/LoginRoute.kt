package id.co.egifcb.peklatour.peklatour.ui.auth.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import id.co.egifcb.peklatour.peklatour.ui.auth.login.model.LoginEventState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoute(
    onSuccessLogin: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(
        key1 = uiState.value.isSuccess,
        block = {
            if (uiState.value.isSuccess) {
                onSuccessLogin.invoke()
            }
        }
    )

    LaunchedEffect(
        key1 = uiState.value.message,
        block = {
            if (uiState.value.message.isNotEmpty()) {
                Toast.makeText(context, uiState.value.message, Toast.LENGTH_SHORT).show()
            }
        }
    )

    LoginScreen(
        email = viewModel.email,
        onEmailChange = {
            viewModel.onEvent(
                event = LoginEventState.EmailOnChange(it)
            )
        },
        password = viewModel.password,
        onPasswordChange = {
            viewModel.onEvent(
                event = LoginEventState.PasswordOnChange(it)
            )
        },
        onClickLogin = {
            viewModel.onEvent(
                event = LoginEventState.Login
            )
        },
        loading = uiState.value.loading
    )
}