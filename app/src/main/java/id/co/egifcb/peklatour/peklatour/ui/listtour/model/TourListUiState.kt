package id.co.egifcb.peklatour.peklatour.ui.listtour.model

import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourList

data class TourListUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val listTour: List<TourList> = listOf(),
    val messageError: String = ""
)
