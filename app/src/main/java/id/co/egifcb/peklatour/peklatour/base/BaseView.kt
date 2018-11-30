package id.co.egifcb.peklatour.peklatour.base

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onFailed(message: String?)
    fun onError(message: String?)
}