package id.co.egifcb.peklatour.peklatour.data.repository.tour.model

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.TourListResponse

data class TourList(
    val detail: String,
    val durationTour: String,
    val exclude: String,
    val price: Int,
    val image: String,
    val include: String,
    val typePlace: String,
    val maximumPax: Int,
    val minimumPax: Int,
    val placeName: String,
    val no: Int,
    val travelRoute: String
)

fun List<TourListResponse>.mapping(): List<TourList> {
    val newList: MutableList<TourList> = mutableListOf()

    this.forEach {
        newList.add(
            TourList(
                detail = it.detail,
                durationTour = it.durationTour,
                exclude = it.exclude,
                price = it.price,
                image = it.image,
                include = it.include,
                typePlace = it.typePlace,
                maximumPax = it.maximumPax,
                minimumPax = it.minimumPax,
                placeName = it.placeName,
                no = it.no,
                travelRoute = it.travelRoute
            )
        )
    }

    return newList.toList()
}
