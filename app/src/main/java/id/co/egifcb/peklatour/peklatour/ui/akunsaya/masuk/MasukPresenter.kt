package id.co.egifcb.peklatour.peklatour.ui.akunsaya.masuk

import id.co.egifcb.peklatour.peklatour.api.ApiConfig
import id.co.egifcb.peklatour.peklatour.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasukPresenter(private val view: MasukView) {
    fun login(email: String?, password: String) {
        view.showLoading()

        val config = ApiConfig.config()
        val call = config.login("login", email, password)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                view.onError(t.message)
                view.hideLoading()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when(response.code()) {
                    200 -> {
                        view.onSuccess(response.body()?.login, response.body()?.message)
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