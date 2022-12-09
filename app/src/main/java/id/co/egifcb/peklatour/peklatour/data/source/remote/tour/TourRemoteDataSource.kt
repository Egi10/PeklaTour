package id.co.egifcb.peklatour.peklatour.data.source.remote.tour

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.OrderListResponse

interface TourRemoteDataSource {
    suspend fun getOrder(idUser: Int): List<OrderListResponse>
}