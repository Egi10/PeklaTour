package id.co.egifcb.peklatour.peklatour.ui.pesanan

import id.co.egifcb.peklatour.peklatour.base.BaseView
import id.co.egifcb.peklatour.peklatour.model.DaftarpesananItem

interface PesananView : BaseView {
    fun onSuccess(list: List<DaftarpesananItem>?)
    fun onEmpty()
}