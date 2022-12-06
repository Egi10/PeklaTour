package id.co.egifcb.peklatour.peklatour.ui.order

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourEmpty

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier
) {
    PeklaTourEmpty(
        imageId = R.drawable.ic_caravan,
        title = stringResource(id = R.string.message_pemesanan)
    )
}