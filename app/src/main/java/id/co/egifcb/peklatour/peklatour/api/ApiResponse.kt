package id.co.egifcb.peklatour.peklatour.api

import com.google.gson.annotations.SerializedName
import id.co.egifcb.peklatour.peklatour.model.*

data class ApiResponse(
	@field:SerializedName("jenistour")
	val jenisTour: List<JenisTourItem>? = null,

	@field:SerializedName("destinasifavorite")
	val destinasiFavorite: List<DestinasifavoriteItem>? = null,

	@field:SerializedName("promotour")
	val promotour: List<PromotourItem>? = null,

	@field:SerializedName("daftartour")
	val daftartour: List<DaftartourItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("login")
	val login: LoginItem? = null,

	@field:SerializedName("daftarpesanan")
	val daftarpesanan: List<DaftarpesananItem>? = null
)