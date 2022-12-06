package id.co.egifcb.peklatour.peklatour.ui.order

import id.co.egifcb.peklatour.peklatour.base.BaseView
import id.co.egifcb.peklatour.peklatour.model.DaftarpesananItem

interface PesananView : BaseView {
    fun onSuccess(list: List<DaftarpesananItem>?)
    fun onEmpty()
}