package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response


import com.google.gson.annotations.SerializedName

data class TourTypeResponse(
    @SerializedName("image")
    val image: String,
    @SerializedName("jenis_wisata")
    val typeOfTravel: String,
    @SerializedName("no")
    val no: Int
)