package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.AuthRemoteDataSource
import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.AuthRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(get())
    }
}