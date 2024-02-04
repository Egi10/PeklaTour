package id.co.egifcb.peklatour.peklatour.ui.order.model

import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order

data class OrderUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val order: List<Order> = listOf(),
    val error: String = "",
    val isEmpty: Boolean = false,
    val isLogin: Boolean = false
)
