package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("biaya_tour")
    val tourFee: Int,
    @SerializedName("durasi_tour")
    val durationTour: String,
    @SerializedName("id_daftar_tour")
    val idRegisterTour: Int,
    @SerializedName("id_pemesan")
    val idBuyer: Int,
    @SerializedName("jumlah_penumpang")
    val totalPassenger: String,
    @SerializedName("no")
    val no: Int,
    @SerializedName("status_pesanan")
    val orderStatus: String,
    @SerializedName("tanggal_berangkat")
    val dateOfDeparture: String,
    @SerializedName("tujuan_tour")
    val destinationTour: String
)