package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.routes

import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response.OrderResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TourService {
    @GET("exec")
    suspend fun getOrder(
        @Query("action") action: String
    ): OrderResponse
}