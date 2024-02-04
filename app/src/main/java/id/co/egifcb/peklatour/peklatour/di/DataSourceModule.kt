package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.AuthRemoteDataSource
import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.AuthRemoteDataSourceImpl
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.TourRemoteDataSource
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.TourRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(get())
    }

    single<TourRemoteDataSource> {
        TourRemoteDataSourceImpl(get())
    }
}