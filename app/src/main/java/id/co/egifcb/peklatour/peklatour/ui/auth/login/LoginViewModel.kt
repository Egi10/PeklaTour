package id.co.egifcb.peklatour.peklatour.ui.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.egifcb.peklatour.peklatour.data.repository.auth.AuthRepository
import id.co.egifcb.peklatour.peklatour.ui.auth.login.model.LoginEventState
import id.co.egifcb.peklatour.peklatour.ui.auth.login.model.LoginUiState
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    /**
     * https://medium.com/androiddevelopers/effective-state-management-for-textfield-in-compose-d6e5b070fbe5
     */
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()


    fun onEvent(event: LoginEventState) {
        when (event) {
            is LoginEventState.EmailOnChange -> {
                email = event.value
            }

            is LoginEventState.PasswordOnChange -> {
                password = event.value
            }

            is LoginEventState.Login -> {
                login(
                    email = email,
                    password = password
                )
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(
                email = email,
                password = password
            ).collectLatest {
                when (it) {
                    is PeklaTourResult.Loading -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = true
                            )
                        }
                    }

                    is PeklaTourResult.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                message = it.data.message,
                                isSuccess = true
                            )
                        }
                    }

                    is PeklaTourResult.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                message = it.exception ?: "Error",
                                isSuccess = false
                            )
                        }
                    }
                }
            }
        }
    }
}