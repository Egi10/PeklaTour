package id.co.egifcb.peklatour.peklatour.ui.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourAlertDialog
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourEmpty
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourError
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderRoute(
    detailOnClick: (Order) -> Unit,
    viewModel: OrderViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val openDialog = remember {
        mutableStateOf(false)
    }
    val messageDialog = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    if (uiState.value.isLoading) {
        PeklaTourLoading()
    }

    if (uiState.value.isSuccess) {
        OrderScreen(
            order = uiState.value.order,
            orderOnClick = {
                when (it.orderStatus) {
                    "Pengajuan" -> {
                        messageDialog.value = context.getString(R.string.message_please_wait_order)
                        openDialog.value = true
                    }

                    "Pengajuan Ditolak" -> {
                        messageDialog.value = context.getString(R.string.message_not_process_order)
                        openDialog.value = true
                    }

                    else -> {
                        detailOnClick.invoke(it)
                    }
                }
            }
        )
    }

    if (uiState.value.isEmpty) {
        PeklaTourEmpty(
            imageId = R.drawable.ic_caravan,
            title = stringResource(id = R.string.message_pemesanan)
        )
    }

    if (!uiState.value.isLogin) {
        PeklaTourEmpty(
            imageId = R.drawable.ic_caravan,
            title = stringResource(id = R.string.message_show_order_not_login)
        )
    }

    if (uiState.value.isError) {
        PeklaTourError(
            message = uiState.value.error,
            onClick = {
                viewModel.getOrder()
            }
        )
    }

    if (openDialog.value) {
        PeklaTourAlertDialog(
            text = messageDialog.value,
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                openDialog.value = false
            },
            dismissButton = {
                openDialog.value = false
            }
        )
    }
}