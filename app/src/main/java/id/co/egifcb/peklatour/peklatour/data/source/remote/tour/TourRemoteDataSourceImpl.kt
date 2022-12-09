package id.co.egifcb.peklatour.peklatour.data.source.remote.tour

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.OrderListResponse
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.routes.TourService
import id.co.egifcb.peklatour.peklatour.data.source.remote.utils.ActionMode

class TourRemoteDataSourceImpl(
    private val tourService: TourService
) : TourRemoteDataSource {
    override suspend fun getOrder(idUser: Int): List<OrderListResponse> {
        return tourService.getOrder(
            action = ActionMode.ORDER.value
        ).orderListResponse.filter {
            it.idBuyer == idUser
        }
    }
}