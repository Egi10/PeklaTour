package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response


import com.google.gson.annotations.SerializedName

data class PromoTourResponse(
    @SerializedName("image")
    val image: String,
    @SerializedName("no")
    val no: Int
)