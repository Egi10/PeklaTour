package id.co.egifcb.peklatour.peklatour.data.source.remote.auth.routes

import id.co.egifcb.peklatour.peklatour.api.ApiResponse
import id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @FormUrlEncoded
    @POST("exec")
    fun register(
        @Query("action") action: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nama") name: String
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("exec")
    suspend fun login(
        @Query("action") action: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}