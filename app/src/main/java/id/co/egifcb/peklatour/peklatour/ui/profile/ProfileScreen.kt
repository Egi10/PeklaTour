package id.co.egifcb.peklatour.peklatour.ui.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourCard
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme
import id.co.egifcb.peklatour.peklatour.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    logoutOnClick: () -> Unit
) {
    PeklaTourCard(
        modifier = modifier
            .padding(
                all = 10.dp
            )
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconDoubleText(
                drawable = R.drawable.ic_user,
                title = stringResource(id = R.string.nama),
                subTitle = name
            )

            Divider(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp
                    )
            )

            IconDoubleText(
                drawable = R.drawable.ic_mail,
                title = stringResource(id = R.string.email),
                subTitle = email
            )

            Divider(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp
                    )
            )

            IconDoubleText(
                drawable = R.drawable.ic_logout,
                title = stringResource(id = R.string.keluar),
                subTitle = stringResource(id = R.string.keluar_dari_akun_anda),
                onClick = logoutOnClick,
                enable = true
            )
        }
    }
}

@Composable
private fun IconDoubleText(
    @DrawableRes drawable: Int,
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enable: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = enable,
                onClick = {
                    onClick?.invoke()
                }
            )
            .padding(
                all = 20.dp
            )
    ) {
        Icon(
            painter = painterResource(id = drawable),
            contentDescription = title,
            tint = Color.Unspecified
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = 15.dp
                )
        )

        Column {
            Text(
                text = title,
                color = Color.Black,
                style = PeklaTourTheme.typography.title,
                fontSize = 18.sp
            )

            Text(
                text = subTitle,
                style = PeklaTourTheme.typography.caption
            )
        }
    }
}

@Preview()
@Composable
private fun ProfileScreenPreview() {
    PeklaTourTheme {
        ProfileScreen(
            name = "Egi",
            email = "egi10",
            logoutOnClick = {}
        )
    }
}