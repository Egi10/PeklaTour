package id.co.egifcb.peklatour.peklatour.ui.daftartour

import id.co.egifcb.peklatour.peklatour.base.BaseView
import id.co.egifcb.peklatour.peklatour.model.DaftartourItem

interface DaftarTourView : BaseView {
    fun onSuccess(list: List<DaftartourItem>?)
    fun onEmpty()
}