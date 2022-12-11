package id.co.egifcb.peklatour.peklatour.data.repository.tour.model

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.OrderResponse

data class Order(
    val tourFee: Int,
    val durationTour: String,
    val idRegisterTour: Int,
    val idBuyer: Int,
    val totalPassenger: String,
    val no: Int,
    val orderStatus: String,
    val dateOfDeparture: String,
    val destinationTour: String
)

fun List<OrderResponse>.mapping(): List<Order> {
    val list: MutableList<Order> = mutableListOf()

    this.forEach {
        list.add(
            Order(
                tourFee = it.tourFee,
                durationTour = it.durationTour,
                idRegisterTour = it.idRegisterTour,
                idBuyer = it.idBuyer,
                totalPassenger = it.totalPassenger,
                no = it.no,
                orderStatus = it.orderStatus,
                dateOfDeparture = it.dateOfDeparture,
                destinationTour = it.destinationTour
            )
        )
    }

    return list.toList()
}
