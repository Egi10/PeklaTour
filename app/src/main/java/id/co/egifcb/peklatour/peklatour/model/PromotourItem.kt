package id.co.egifcb.peklatour.peklatour.model

import com.google.gson.annotations.SerializedName

data class PromotourItem(

	@field:SerializedName("no")
	val no: Int? = null,

	@field:SerializedName("image")
	val image: String? = null
)