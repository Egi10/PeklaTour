package id.co.egifcb.peklatour.peklatour.ui.home.model

import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.DestinationFavorite
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Promo
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourType

data class HomeUiState(
    val loading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val promo: List<Promo> = listOf(),
    val destinationFavorite: List<DestinationFavorite> = listOf(),
    val tourType: List<TourType> = listOf(),
    val error: String = "",
)
