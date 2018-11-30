package id.co.egifcb.peklatour.peklatour.ui.akunsaya.daftar

import id.co.egifcb.peklatour.peklatour.api.ApiConfig
import id.co.egifcb.peklatour.peklatour.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaftarPresenter(private val view: DaftarView) {
    fun register(email: String?, password: String?, nama: String?) {
        view.showLoading()

        val config = ApiConfig.config()
        val call = config.register("register", email, password, nama)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                view.onError(t.message)
                view.hideLoading()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when(response.code()) {
                    200 -> {
                        view.onSuccess(response.body()?.message)
                    }

                    else -> {
                        view.onFailed(response.message())
                    }
                }
                view.hideLoading()
            }

        })
    }
}