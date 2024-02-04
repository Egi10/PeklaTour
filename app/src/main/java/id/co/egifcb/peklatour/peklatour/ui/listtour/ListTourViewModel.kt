package id.co.egifcb.peklatour.peklatour.ui.listtour

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.egifcb.peklatour.peklatour.data.repository.tour.TourRepository
import id.co.egifcb.peklatour.peklatour.ui.listtour.model.TourListUiState
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListTourViewModel(
    private val tourRepository: TourRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TourListUiState())
    val uiState get() = _uiState.asStateFlow()

    fun getListTour(tourType: String) {
        viewModelScope.launch {
            tourRepository.getTourList(typeTour = tourType)
                .collectLatest {
                    when (it) {
                        is PeklaTourResult.Loading -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    isSuccess = false,
                                    isError = false,
                                    isEmpty = false
                                )
                            }
                        }

                        is PeklaTourResult.Success -> {

                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    isError = false,
                                    isEmpty = it.data.isEmpty(),
                                    listTour = it.data
                                )
                            }
                        }

                        is PeklaTourResult.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    isError = true,
                                    isEmpty = false,
                                    messageError = it.exception ?: "Error"
                                )
                            }
                        }
                    }
                }
        }
    }
}