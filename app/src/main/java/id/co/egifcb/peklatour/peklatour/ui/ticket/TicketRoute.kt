package id.co.egifcb.peklatour.peklatour.ui.ticket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order
import net.glxn.qrgen.android.QRCode

@Composable
fun TicketRoute(
    order: Order?
) {
    Box(
        modifier = Modifier
            .padding(
                all = 24.dp
            )
    ) {
        TicketScreen(
            noOrder = order?.no.toString(),
            tourFee = order?.tourFee?.toString() ?: "0",
            destinationTour = order?.destinationTour ?: "",
            totalPassenger = order?.totalPassenger ?: "",
            durationTour = order?.durationTour ?: "",
            imageQR = QRCode.from(order?.no.toString()).withSize(450, 450).bitmap()
        )
    }
}