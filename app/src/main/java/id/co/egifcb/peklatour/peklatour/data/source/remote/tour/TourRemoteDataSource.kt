package id.co.egifcb.peklatour.peklatour.data.source.remote.tour

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.*

interface TourRemoteDataSource {
    suspend fun getOrder(idUser: Int): List<OrderResponse>
    suspend fun getTourType(): List<TourTypeResponse>
    suspend fun getDestinationFavorite(): List<DestinationFavoriteResponse>
    suspend fun getPromo(): List<PromoTourResponse>
    suspend fun getTourList(): List<TourListResponse>
}