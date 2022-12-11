package id.co.egifcb.peklatour.peklatour.data.repository.tour

import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Home
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.mapping
import id.co.egifcb.peklatour.peklatour.data.source.local.PreferencesUser
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.TourRemoteDataSource
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import id.co.egifcb.peklatour.peklatour.until.asResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class TourRepositoryImpl(
    private val tourRemoteDataSource: TourRemoteDataSource,
    private val preferencesUser: PreferencesUser
) : TourRepository {
    override fun getOrder(): Flow<PeklaTourResult<List<Order>>> {
        return flow {
            val user = preferencesUser.getUserDetail()
            val id = user[PreferencesUser.NO]

            if (id != null) {
                emit(
                    tourRemoteDataSource.getOrder(
                        idUser = id.toInt()
                    )
                )
            } else {
                emit(
                    listOf()
                )
            }
        }.map {
            it.mapping()
        }.asResult()
    }

    override fun getHome(): Flow<PeklaTourResult<Home>> {
        return flow {
            val promo = tourRemoteDataSource.getPromo()
            val tourType = tourRemoteDataSource.getTourType()
            val destinationFavorite = tourRemoteDataSource.getDestinationFavorite()

            emit(
                Triple(
                    promo, tourType, destinationFavorite
                )
            )
        }.map {
            it.mapping()
        }.asResult().flowOn(Dispatchers.IO)
    }


}