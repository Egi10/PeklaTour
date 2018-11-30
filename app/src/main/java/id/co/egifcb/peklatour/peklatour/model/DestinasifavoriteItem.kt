package id.co.egifcb.peklatour.peklatour.model

import com.google.gson.annotations.SerializedName

data class DestinasifavoriteItem(

	@field:SerializedName("no")
	val no: Int? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("harga")
	val harga: Int? = null,

	@field:SerializedName("nama_tempat")
	val namaTempat: String? = null,

	@field:SerializedName("lama_perjalanan")
	val lamaPerjalanan: String? = null
)