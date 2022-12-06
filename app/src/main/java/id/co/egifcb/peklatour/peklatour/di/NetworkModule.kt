package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.BuildConfig
import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.routes.AuthService
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
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun configAuthService(retrofit: Retrofit): AuthService {
    return retrofit.create(AuthService::class.java)
}