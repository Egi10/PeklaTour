package id.co.egifcb.peklatour.peklatour.ui.auth.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import id.co.egifcb.peklatour.peklatour.ui.auth.register.model.RegisterEventState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoute(
    onSuccessRegister: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(
        key1 = uiState.value.isSuccess,
        block = {
            if (uiState.value.isSuccess) {
                onSuccessRegister.invoke()
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

    RegisterScreen(
        name = viewModel.name,
        onNameChange = {
            viewModel.onEvent(
                event = RegisterEventState.NameOnChange(it)
            )
        },
        email = viewModel.email,
        onEmailChange = {
            viewModel.onEvent(
                event = RegisterEventState.EmailOnChange(it)
            )
        },
        password = viewModel.password,
        onPasswordChange = {
            viewModel.onEvent(
                event = RegisterEventState.PasswordOnChange(it)
            )
        },
        confirmationPassword = viewModel.confirmationPassword,
        onConfirmationPasswordChange = {
            viewModel.onEvent(
                event = RegisterEventState.ConfirmationPasswordOnChange(it)
            )
        },
        registerOnClick = {
            viewModel.onEvent(
                event = RegisterEventState.Register
            )
        },
        loading = uiState.value.loading,
        enableRegister = viewModel.validRegister
    )
}