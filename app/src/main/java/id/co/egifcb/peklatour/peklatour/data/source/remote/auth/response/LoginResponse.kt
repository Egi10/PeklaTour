package id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("login")
    val authResponse: AuthResponse?,
    @SerializedName("message")
    val message: String
)