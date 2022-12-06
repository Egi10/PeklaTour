package id.co.egifcb.peklatour.peklatour.data.repository.auth

import id.co.egifcb.peklatour.peklatour.data.repository.auth.model.Login
import id.co.egifcb.peklatour.peklatour.data.source.local.PreferencesUser
import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.AuthRemoteDataSource
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import id.co.egifcb.peklatour.peklatour.until.fetchError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val preferencesUser: PreferencesUser
) : AuthRepository {
    @OptIn(FlowPreview::class)
    override fun login(email: String, password: String): Flow<PeklaTourResult<Login>> {
        return flow {
            emit(
                authRemoteDataSource.login(
                    email = email,
                    password = password
                )
            )
        }.flatMapMerge {
            it.login?.let { login ->
                preferencesUser.createLogin(
                    no = login.no.toString(),
                    name = login.name,
                    email = login.name
                )
            }

            return@flatMapMerge flow {
                if (it.login == null) {
                    emit(
                        PeklaTourResult.Error(
                            it.message
                        )
                    )
                } else {
                    emit(
                        PeklaTourResult.Success(
                            Login(
                                message = it.message
                            )
                        )
                    )
                }
            }
        }.onStart {
            emit(PeklaTourResult.Loading)
        }.catch {
            fetchError(it)
        }.flowOn(Dispatchers.IO)
    }
}