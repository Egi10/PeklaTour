package id.co.egifcb.peklatour.peklatour.until

import kotlinx.coroutines.flow.*
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

sealed interface PeklaTourResult<out T> {
    data class Success<T>(val data: T) : PeklaTourResult<T>
    data class Error(val exception: String? = null) : PeklaTourResult<Nothing>
    object Loading : PeklaTourResult<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<PeklaTourResult<T>> {
    return this
        .map<T, PeklaTourResult<T>> {
            PeklaTourResult.Success(it)
        }
        .onStart {
            emit(PeklaTourResult.Loading)
        }
        .catch {
            emit(fetchError(it))
        }
}

/**
 * Response @link [https://developer.mozilla.org/en-US/docs/Web/HTTP/Status]
 */
fun fetchError(throwable: Throwable): PeklaTourResult.Error {
    return when (throwable) {
        is IOException -> {
            PeklaTourResult.Error(throwable.message)
        }

        is HttpException -> {
            when (throwable.code()) {
                in 400..499 -> {
                    val response = throwable.response()?.errorBody()?.string()

                    val jsonObject = response?.let { JSONObject(it) }
                    val message = jsonObject?.getString("message")

                    PeklaTourResult.Error(message)
                }

                else -> {
                    PeklaTourResult.Error(throwable.message)
                }
            }
        }

        else -> {
            PeklaTourResult.Error(throwable.message)
        }
    }
}

fun <T> PeklaTourResult<T>.isLoading(): Boolean {
    return when (this) {
        is PeklaTourResult.Loading -> {
            true
        }

        is PeklaTourResult.Success<T> -> {
            false
        }

        is PeklaTourResult.Error -> {
            false
        }
    }
}