package id.co.egifcb.peklatour.peklatour.model

import com.google.gson.annotations.SerializedName

data class JenisTourItem(
	@field:SerializedName("no")
	val no: Int? = null,

	@field:SerializedName("jenis_wisata")
	val jenisWisata: String? = null,

	@field:SerializedName("image")
	val image: String? = null
)