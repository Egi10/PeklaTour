package id.co.egifcb.peklatour.peklatour.ui.akunsaya.masuk

import id.co.egifcb.peklatour.peklatour.base.BaseView
import id.co.egifcb.peklatour.peklatour.model.LoginItem

interface MasukView : BaseView {
    fun onSuccess(list: LoginItem?, message: String?)
}