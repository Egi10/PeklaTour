package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.data.repository.auth.AuthRepository
import id.co.egifcb.peklatour.peklatour.data.repository.auth.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
}