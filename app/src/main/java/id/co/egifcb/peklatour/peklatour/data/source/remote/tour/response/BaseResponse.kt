package id.co.egifcb.peklatour.peklatour.data.source.remote.tour.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val data: List<T>
)
