package id.co.egifcb.peklatour.peklatour.ui.order

import id.co.egifcb.peklatour.peklatour.api.ApiConfig
import id.co.egifcb.peklatour.peklatour.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PesananPresenter(private val view: PesananView) {
    fun getPesanan(id: String) {
        view.showLoading()

        val config = ApiConfig.config()
        val call = config.getTour("daftarpesanan")
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                view.onError(t.message)
                view.hideLoading()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when(response.code()) {
                    200 -> {
                        val list = response.body()?.daftarpesanan

                        val filter = list?.filter {
                            it.idPemesan.endsWith(id)
                        }

                        if (filter.isNullOrEmpty()) {
                            view.onEmpty()
                        } else {
                            view.onSuccess(filter)
                        }
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