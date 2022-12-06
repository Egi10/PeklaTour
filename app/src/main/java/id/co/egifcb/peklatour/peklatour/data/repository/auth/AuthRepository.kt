package id.co.egifcb.peklatour.peklatour.data.repository.auth

import id.co.egifcb.peklatour.peklatour.data.repository.auth.model.Login
import id.co.egifcb.peklatour.peklatour.until.PeklaTourResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(
        email: String,
        password: String
    ): Flow<PeklaTourResult<Login>>
}