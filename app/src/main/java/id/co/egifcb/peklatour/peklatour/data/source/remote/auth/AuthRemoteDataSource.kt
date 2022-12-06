package id.co.egifcb.peklatour.peklatour.data.source.remote.auth

import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response.LoginResponse

interface AuthRemoteDataSource {
    suspend fun login(
        email: String,
        password: String
    ): LoginResponse
}