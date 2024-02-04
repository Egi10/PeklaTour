package id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("nama")
    val name: String,
    @SerializedName("no")
    val no: Int
)