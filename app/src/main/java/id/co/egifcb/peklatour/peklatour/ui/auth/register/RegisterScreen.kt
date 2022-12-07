package id.co.egifcb.peklatour.peklatour.ui.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourButton
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourTextField
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun RegisterScreen(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmationPassword: String,
    onConfirmationPasswordChange: (String) -> Unit,
    registerOnClick: () -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                all = 24.dp
            ),
        verticalArrangement = Arrangement
            .spacedBy(24.dp)
    ) {
        PeklaTourTextField(
            value = name,
            onValueChange = onNameChange,
            label = stringResource(id = R.string.nama_lengkap)
        )

        PeklaTourTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.email)
        )

        PeklaTourTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.password),
            isPassword = true
        )

        PeklaTourTextField(
            value = confirmationPassword,
            onValueChange = onConfirmationPasswordChange,
            label = stringResource(id = R.string.konfirmasi_password),
            isPassword = true
        )

        PeklaTourButton(
            text = stringResource(id = R.string.daftar_sekarang),
            onClick = registerOnClick,
            loading = loading
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RegisterScreenPreview() {
    PeklaTourTheme {
        RegisterScreen(
            name = "",
            onNameChange = {},
            email = "",
            onEmailChange = {},
            password = "",
            onPasswordChange = {},
            confirmationPassword = "",
            onConfirmationPasswordChange = {},
            registerOnClick = {},
            loading = false
        )
    }
}