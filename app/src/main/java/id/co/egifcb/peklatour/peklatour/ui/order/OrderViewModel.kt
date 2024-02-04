package id.co.egifcb.peklatour.peklatour.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.egifcb.peklatour.peklatour.data.repository.tour.TourRepository
import id.co.egifcb.peklatour.peklatour.data.source.local.PreferencesUser
import id.co.egifcb.peklatour.peklatour.ui.order.model.OrderUiState
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderViewModel(
    private val tourRepository: TourRepository,
    private val preferencesUser: PreferencesUser
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                isLogin = preferencesUser.isLoggedIn()
            )
        }

        if (preferencesUser.isLoggedIn()) {
            getOrder()
        }
    }

    fun getOrder() {
        viewModelScope.launch {
            tourRepository.getOrder()
                .collectLatest {
                    when (it) {
                        is PeklaTourResult.Loading -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    isSuccess = false,
                                    isError = false
                                )
                            }
                        }

                        is PeklaTourResult.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    order = it.data,
                                    isEmpty = it.data.isEmpty(),
                                    isSuccess = true,
                                    isError = false
                                )
                            }
                        }

                        is PeklaTourResult.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = it.exception ?: "Error",
                                    isSuccess = false,
                                    isError = true
                                )
                            }
                        }
                    }
                }
        }
    }
}