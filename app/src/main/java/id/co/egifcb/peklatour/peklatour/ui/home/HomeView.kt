package id.co.egifcb.peklatour.peklatour.ui.home

import id.co.egifcb.peklatour.peklatour.base.BaseView
import id.co.egifcb.peklatour.peklatour.model.DestinasifavoriteItem
import id.co.egifcb.peklatour.peklatour.model.JenisTourItem
import id.co.egifcb.peklatour.peklatour.model.PromotourItem

interface HomeView : BaseView {
    fun onSuccess(list: List<JenisTourItem>?)
    fun onEmpty()
    //Favorite
    fun onSuccessFavorite(list: List<DestinasifavoriteItem>?)
    //Promo
    fun onSuccessPromo(list: List<PromotourItem>?)
}