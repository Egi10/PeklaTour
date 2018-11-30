package id.co.egifcb.peklatour.peklatour.model

import com.google.gson.annotations.SerializedName

data class LoginItem(

	@field:SerializedName("no")
	val no: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)