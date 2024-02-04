package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.routes

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.*
import id.co.egifcb.peklatour.peklatour.data.source.remote.utils.ActionMode
import retrofit2.http.GET
import retrofit2.http.Query

interface TourService {
    @GET("exec")
    suspend fun getOrder(
        @Query("action") action: String = ActionMode.ORDER.value
    ): BaseResponse<OrderResponse>

    @GET("exec")
    suspend fun getTourType(
        @Query("action") action: String = ActionMode.TOUR_TYPE.value
    ): BaseResponse<TourTypeResponse>

    @GET("exec")
    suspend fun getDestinationFavorite(
        @Query("action") action: String = ActionMode.DESTINATION_FAVORITE.value
    ): BaseResponse<DestinationFavoriteResponse>

    @GET("exec")
    suspend fun getPromoTour(
        @Query("action") action: String = ActionMode.PROMO_TOUR.value
    ): BaseResponse<PromoTourResponse>

    @GET("exec")
    suspend fun getTourList(
        @Query("action") action: String = ActionMode.TOUR_LIST.value
    ): BaseResponse<TourListResponse>
}