package id.co.egifcb.peklatour.peklatour.data.repository.tour

import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Home
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import kotlinx.coroutines.flow.Flow

interface TourRepository {
    fun getOrder(): Flow<PeklaTourResult<List<Order>>>
    fun getHome(): Flow<PeklaTourResult<Home>>
}