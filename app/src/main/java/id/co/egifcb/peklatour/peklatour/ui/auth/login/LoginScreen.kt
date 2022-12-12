package id.co.egifcb.peklatour.peklatour.ui.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourButton
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourTextField

@Composable
fun LoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onClickLogin: () -> Unit,
    loading: Boolean,
    enableLogin: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                all = 24.dp
            ),
        verticalArrangement = Arrangement
            .spacedBy(24.dp),
    ) {
        PeklaTourTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.email)
        )

        PeklaTourTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.password),
            isPassword = true,
        )

        PeklaTourButton(
            text = stringResource(id = R.string.masuk_sekarang),
            onClick = onClickLogin,
            loading = loading,
            enabled = enableLogin
        )
    }
}