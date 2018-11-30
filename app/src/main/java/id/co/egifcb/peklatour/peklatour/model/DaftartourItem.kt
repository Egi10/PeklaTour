package id.co.egifcb.peklatour.peklatour.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DaftartourItem(

	@field:SerializedName("no")
	val no: Int? = null,

	@field:SerializedName("include")
	val include: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("maximum_pax")
	val maximumPax: Int? = null,

	@field:SerializedName("rute_perjalanan")
	val rutePerjalanan: String? = null,

	@field:SerializedName("exclute")
	val exclute: String? = null,

	@field:SerializedName("minimum_pax")
	val minimumPax: Int? = null,

	@field:SerializedName("nama_tempat")
	val namaTempat: String? = null,

	@field:SerializedName("jenis_tempat")
	val jenisTempat: String,

	@field:SerializedName("durasi_tour")
	val durasiTour: String? = null
): Parcelable