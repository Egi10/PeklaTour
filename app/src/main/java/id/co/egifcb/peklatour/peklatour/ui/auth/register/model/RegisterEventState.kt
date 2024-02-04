package id.co.egifcb.peklatour.peklatour.ui.auth.register.model

sealed class RegisterEventState {
    data class NameOnChange(val value: String) : RegisterEventState()
    data class EmailOnChange(val value: String) : RegisterEventState()
    data class PasswordOnChange(val value: String) : RegisterEventState()
    data class ConfirmationPasswordOnChange(val value: String) : RegisterEventState()
    object Register : RegisterEventState()
}
