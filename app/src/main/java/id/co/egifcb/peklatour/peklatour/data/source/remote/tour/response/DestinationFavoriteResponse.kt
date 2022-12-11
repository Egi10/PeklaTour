package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response


import com.google.gson.annotations.SerializedName

data class DestinationFavoriteResponse(
    @SerializedName("harga")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lama_perjalanan")
    val lengthOfJourney: String,
    @SerializedName("nama_tempat")
    val placeName: String,
    @SerializedName("no")
    val no: Int
)