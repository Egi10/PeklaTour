package id.co.egifcb.peklatour.peklatour.ui.home

import id.co.egifcb.peklatour.peklatour.api.ApiConfig
import id.co.egifcb.peklatour.peklatour.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private val view: HomeView) {
    fun getJenisTour() {
        view.showLoading()

        val config = ApiConfig.config()
        val call = config.getTour("jenistour")
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                view.onError(t.message)
                view.hideLoading()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when(response.code()) {
                    200 -> {
                        getJenisTourFavorite()
                        getJenisTourPromo()

                        val jenisTour = response.body()?.jenisTour

                        if (jenisTour.isNullOrEmpty()) {
                            view.onEmpty()
                        } else {
                            view.onSuccess(jenisTour)
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

    fun getJenisTourFavorite() {
        view.showLoading()

        val config = ApiConfig.config()
        val call = config.getTour("destinasifavorite")
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                view.onError(t.message)
                view.hideLoading()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when(response.code()) {
                    200 -> {
                        val jenisTour = response.body()?.destinasiFavorite

                        if (jenisTour.isNullOrEmpty()) {
                            view.onEmpty()
                        } else {
                            view.onSuccessFavorite(jenisTour)
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

    fun getJenisTourPromo() {
        view.showLoading()

        val config = ApiConfig.config()
        val call = config.getTour("promotour")
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                view.onError(t.message)
                view.hideLoading()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when(response.code()) {
                    200 -> {
                        val jenisTour = response.body()?.promotour

                        if (jenisTour.isNullOrEmpty()) {
                            view.onEmpty()
                        } else {
                            view.onSuccessPromo(jenisTour)
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