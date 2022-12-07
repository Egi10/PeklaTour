package id.co.egifcb.peklatour.peklatour.ui.auth.register

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.egifcb.peklatour.peklatour.data.repository.auth.AuthRepository
import id.co.egifcb.peklatour.peklatour.ui.auth.register.model.RegisterEventState
import id.co.egifcb.peklatour.peklatour.ui.auth.register.model.RegisterUiState
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmationPassword by mutableStateOf("")
        private set

    val validConfirmationPassword by derivedStateOf {
        password == confirmationPassword
    }

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: RegisterEventState) {
        when(event) {
            is RegisterEventState.NameOnChange -> {
                name = event.value
            }

            is RegisterEventState.EmailOnChange -> {
                email = event.value
            }

            is RegisterEventState.PasswordOnChange -> {
                password = event.value
            }

            is RegisterEventState.ConfirmationPasswordOnChange -> {
                confirmationPassword = event.value
            }

            is RegisterEventState.Register -> {
                register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            authRepository.register(
                email = email,
                password = password,
                name = name
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