package id.co.egifcb.peklatour.peklatour.data.repository.tour.model

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.DestinationFavoriteResponse
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.PromoTourResponse
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.TourTypeResponse

fun Triple<List<PromoTourResponse>, List<TourTypeResponse>, List<DestinationFavoriteResponse>>.mapping(): Home {
    val tourType: MutableList<TourType> = mutableListOf()
    val destinationFavorite: MutableList<DestinationFavorite> = mutableListOf()
    val promo: MutableList<Promo> = mutableListOf()

    this.first.forEach {
        promo.add(
            Promo(
                image = it.image
            )
        )
    }

    this.second.forEach {
        tourType.add(
            TourType(
                image = it.image,
                typeOfTravel = it.typeOfTravel
            )
        )
    }

    this.third.forEach {
        destinationFavorite.add(
            DestinationFavorite(
                image = it.image,
                placeName = it.placeName,
                lengthOfJourney = it.lengthOfJourney
            )
        )
    }

    return Home(
        tourType = tourType,
        destinationFavorite = destinationFavorite,
        promo = promo
    )
}