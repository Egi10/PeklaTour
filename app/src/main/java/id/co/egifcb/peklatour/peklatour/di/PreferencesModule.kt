package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.data.source.local.PreferencesUser
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single {
        PreferencesUser(androidContext())
    }
}