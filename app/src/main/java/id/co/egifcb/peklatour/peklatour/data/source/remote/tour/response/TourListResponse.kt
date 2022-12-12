package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response


import com.google.gson.annotations.SerializedName

data class TourListResponse(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("durasi_tour")
    val durationTour: String,
    @SerializedName("exclute")
    val exclude: String,
    @SerializedName("harga")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("include")
    val include: String,
    @SerializedName("jenis_tempat")
    val typePlace: String,
    @SerializedName("maximum_pax")
    val maximumPax: Int,
    @SerializedName("minimum_pax")
    val minimumPax: Int,
    @SerializedName("nama_tempat")
    val placeName: String,
    @SerializedName("no")
    val no: Int,
    @SerializedName("rute_perjalanan")
    val travelRoute: String
)