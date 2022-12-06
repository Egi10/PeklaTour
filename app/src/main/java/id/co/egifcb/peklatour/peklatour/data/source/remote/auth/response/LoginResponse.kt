package id.co.egifcb.peklatour.peklatour.data.source.remote.auth.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("login")
    val login: Login?,
    @SerializedName("message")
    val message: String
)

data class Login(
    @SerializedName("email")
    val email: String,
    @SerializedName("nama")
    val name: String,
    @SerializedName("no")
    val no: Int
)