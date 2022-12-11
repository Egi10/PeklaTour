package id.co.egifcb.peklatour.peklatour.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.egifcb.peklatour.peklatour.data.repository.tour.TourRepository
import id.co.egifcb.peklatour.peklatour.ui.home.model.HomeUiState
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tourRepository: TourRepository
): ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getHome()
    }

    fun getHome() {
        viewModelScope.launch {
            tourRepository.getHome()
                .collect {
                    when(it) {
                        is PeklaTourResult.Loading -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = true,
                                    isSuccess = false
                                )
                            }
                        }

                        is PeklaTourResult.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = false,
                                    isSuccess = true,
                                    promo = it.data.promo,
                                    tourType = it.data.tourType,
                                    destinationFavorite = it.data.destinationFavorite
                                )
                            }
                        }

                        is PeklaTourResult.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = false,
                                    isSuccess = false,
                                    error = it.exception ?: "Error"
                                )
                            }
                        }
                    }
                }
        }
    }
}