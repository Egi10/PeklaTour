package id.co.egifcb.peklatour.peklatour.api

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("exec")
    fun getTour(@Query("action") action: String?): Call<ApiResponse>

    @FormUrlEncoded
    @POST("exec")
    fun register(@Query("action") action: String?,
                 @Field("email") email: String?,
                 @Field("password") password: String?,
                 @Field("nama") nama: String?): Call<ApiResponse>

    @FormUrlEncoded
    @POST("exec")
    fun login(@Query("action") action: String?,
              @Field("email") email: String?,
              @Field("password") password: String?): Call<ApiResponse>

    @FormUrlEncoded
    @POST("exec")
    fun pesan(@Query("action") action: String?,
              @Field("id_daftar_tour") idDaftarTour: Int?,
              @Field("tanggal_berangkat") tanggalBerangkat: String?,
              @Field("jumlah_penumpang") jumlahPenumpang: String?,
              @Field("id_pemesan") idPemesan: String?): Call<ApiResponse>
}