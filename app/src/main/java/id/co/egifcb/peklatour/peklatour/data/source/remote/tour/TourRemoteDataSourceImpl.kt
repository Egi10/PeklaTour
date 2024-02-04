package id.co.egifcb.peklatour.peklatour.data.source.remote.tour

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.*
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.routes.TourService

class TourRemoteDataSourceImpl(
    private val tourService: TourService
) : TourRemoteDataSource {
    override suspend fun getOrder(idUser: Int): List<OrderResponse> {
        return tourService.getOrder().data.filter {
            it.idBuyer == idUser
        }
    }

    override suspend fun getTourType(): List<TourTypeResponse> {
        return tourService.getTourType().data
    }

    override suspend fun getDestinationFavorite(): List<DestinationFavoriteResponse> {
        return tourService.getDestinationFavorite().data
    }

    override suspend fun getPromo(): List<PromoTourResponse> {
        return tourService.getPromoTour().data
    }

    override suspend fun getTourList(): List<TourListResponse> {
        return tourService.getTourList().data
    }
}