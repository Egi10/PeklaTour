package id.co.egifcb.peklatour.peklatour.ui.auth.login.model

data class LoginUiState(
    val loading: Boolean = false,
    val message: String = "",
    val isSuccess: Boolean = false
)
