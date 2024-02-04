package id.co.egifcb.peklatour.peklatour.data.source.remote.utils

enum class ActionMode(val value: String) {
    LOGIN("login"),
    REGISTER("register"),
    ORDER("daftarpesanan"),
    TOUR_TYPE("jenistour"),
    DESTINATION_FAVORITE("destinasifavorite"),
    PROMO_TOUR("promotour"),
    TOUR_LIST("daftartour")
}