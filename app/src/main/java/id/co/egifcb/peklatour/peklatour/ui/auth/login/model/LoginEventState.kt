package id.co.egifcb.peklatour.peklatour.ui.auth.login.model

sealed class LoginEventState {
    data class EmailOnChange(val value: String): LoginEventState()
    data class PasswordOnChange(val value: String): LoginEventState()

    object Login: LoginEventState()
}
