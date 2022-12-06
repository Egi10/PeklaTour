package id.co.egifcb.peklatour.peklatour.ui.profile

import androidx.compose.runtime.Composable

@Composable
fun ProfileRoute(
    loginOnClick: () -> Unit,
    registerOnLogin: () -> Unit,
) {
    ProfileScreen(
        loginOnClick = loginOnClick,
        registerOnLogin = registerOnLogin
    )
}