package id.co.egifcb.peklatour.peklatour.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourButton
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun ProfileAuthScreen(
    loginOnClick: () -> Unit,
    registerOnLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                all = 20.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.message_login),
            style = PeklaTourTheme.typography.caption
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 20.dp
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PeklaTourButton(
                text = stringResource(id = R.string.masuk),
                onClick = loginOnClick,
                modifier = Modifier
                    .weight(1f)
            )

            PeklaTourButton(
                text = stringResource(id = R.string.daftar),
                onClick = registerOnLogin,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}