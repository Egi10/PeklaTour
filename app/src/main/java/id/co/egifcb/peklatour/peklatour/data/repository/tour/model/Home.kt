package id.co.egifcb.peklatour.peklatour.data.repository.tour.model

data class Home(
    val tourType: List<TourType>,
    val destinationFavorite: List<DestinationFavorite>,
    val promo: List<Promo>
)

data class TourType(
    val image: String,
    val typeOfTravel: String
)

data class DestinationFavorite(
    val image: String,
    val placeName: String,
    val lengthOfJourney: String
)

data class Promo(
    val image: String
)
