package id.co.egifcb.peklatour.peklatour.ui.profile

import androidx.lifecycle.ViewModel
import id.co.egifcb.peklatour.peklatour.data.source.local.PreferencesUser
import id.co.egifcb.peklatour.peklatour.ui.profile.model.ProfileEventState
import id.co.egifcb.peklatour.peklatour.ui.profile.model.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val preferencesUser: PreferencesUser
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val detailUser = preferencesUser.getUserDetail()
        _uiState.update {
            it.copy(
                isLogin = preferencesUser.isLoggedIn(),
                name = detailUser[PreferencesUser.NAME] ?: "",
                email = detailUser[PreferencesUser.EMAIL] ?: ""
            )
        }
    }

    fun onEvent(event: ProfileEventState) {
        if (event == ProfileEventState.Logout) {
            preferencesUser.logoutUser()

            _uiState.update {
                it.copy(
                    isLogout = true
                )
            }
        }
    }
}