package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.routes.AuthService
import id.co.egifcb.peklatour.peklatour.data.source.remote.tour.routes.TourService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        configOkhttp()
    }

    single {
        configRetrofit(get())
    }

    single {
        configAuthService(get())
    }

    single {
        configTourService(get())
    }
}

fun configOkhttp(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun configRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(
            "${NetworkModule.BASE_URL}/${NetworkModule.DEPLOYMENT_ID}/"
        )
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun configAuthService(retrofit: Retrofit): AuthService {
    return retrofit.create(AuthService::class.java)
}

fun configTourService(retrofit: Retrofit): TourService {
    return retrofit.create(TourService::class.java)
}

private object NetworkModule {
    const val BASE_URL = "https://script.google.com/macros/s"
    const val DEPLOYMENT_ID = "AKfycbw3v1xyqHzzDv9ccgbqUTKuZFbMKUAFKuUXE4ZXTSuvM5sNCju0BiYtdLc_Btk55sP_uQ"
}