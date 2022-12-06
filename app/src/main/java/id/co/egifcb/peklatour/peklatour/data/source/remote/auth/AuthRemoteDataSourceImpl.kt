package id.co.egifcb.peklatour.peklatour.data.source.remote.auth

import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response.LoginResponse
import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.routes.AuthService
import id.co.egifcb.peklatour.peklatour.data.source.remote.utils.ActionMode

class AuthRemoteDataSourceImpl(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): LoginResponse {
        return authService.login(
            action = ActionMode.LOGIN.value,
            email = email,
            password = password
        )
    }
}