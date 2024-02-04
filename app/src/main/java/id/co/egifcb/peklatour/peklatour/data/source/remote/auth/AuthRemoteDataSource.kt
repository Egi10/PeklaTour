package id.co.egifcb.peklatour.peklatour.data.source.remote.auth

import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response.LoginResponse
import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response.RegisterResponse

interface AuthRemoteDataSource {
    suspend fun login(
        email: String,
        password: String
    ): LoginResponse

    suspend fun register(
        email: String,
        password: String,
        name: String
    ): RegisterResponse
}