package id.co.egifcb.peklatour.peklatour.data.repository.tour

import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Home
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourList
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.mapping
import id.co.egifcb.peklatour.peklatour.data.source.local.PreferencesUser
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.TourRemoteDataSource
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import id.co.egifcb.peklatour.peklatour.until.asResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class TourRepositoryImpl(
    private val tourRemoteDataSource: TourRemoteDataSource,
    private val preferencesUser: PreferencesUser,
    private val dispatcher: CoroutineDispatcher
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
        }.asResult().flowOn(dispatcher)
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
        }.asResult().flowOn(dispatcher)
    }

    override fun getTourList(typeTour: String): Flow<PeklaTourResult<List<TourList>>> {
        return flow {
            emit(
                tourRemoteDataSource.getTourList()
            )
        }.map {
            it.mapping()
        }.map {
            it.filter { tour ->
                tour.typePlace == typeTour
            }
        }.asResult().flowOn(dispatcher)
    }
}