package id.co.egifcb.peklatour.peklatour

import android.app.Application
import id.co.egifcb.peklatour.peklatour.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule,
                preferencesModule,
                dataSourceModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}