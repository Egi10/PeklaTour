package id.co.egifcb.peklatour.peklatour.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourCard
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme
import id.co.egifcb.peklatour.peklatour.until.formatRupiah

@Composable
fun OrderScreen(
    order: List<Order>,
    orderOnClick: (Order) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement
                .spacedBy(16.dp),
            content = {
                items(
                    order,
                    key = {
                        it.no
                    }
                ) { order ->
                    ItemOrder(
                        no = order.no,
                        tourFee = order.tourFee,
                        destinationTour = order.destinationTour,
                        orderStatus = order.orderStatus,
                        onClick = {
                            orderOnClick.invoke(order)
                        }
                    )
                }
            }
        )
    }
}

@Composable
private fun ItemOrder(
    no: Int,
    tourFee: Int,
    destinationTour: String,
    orderStatus: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PeklaTourCard(
        modifier = modifier
            .clickable {
                onClick.invoke()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 10.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.no_pesanan, no),
                    style = PeklaTourTheme.typography.caption,
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = tourFee.toString().formatRupiah() ?: "",
                    style = PeklaTourTheme.typography.title,
                    fontSize = 16.sp
                )
            }

            Text(
                text = destinationTour,
                style = PeklaTourTheme.typography.title,
                fontSize = 18.sp,
                color = Color.Black
            )

            if (orderStatus == "Pengajuan Ditolak") {
                BoxText(
                    text = orderStatus,
                    color = Color.Red
                )
            } else {
                BoxText(
                    text = orderStatus
                )
            }
        }
    }
}

@Composable
fun BoxText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(
                color = color
            )
    ) {
        Text(
            text = text,
            style = PeklaTourTheme.typography.caption,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .padding(
                    top = 3.dp,
                    bottom = 3.dp,
                    start = 6.dp,
                    end = 6.dp
                )
        )
    }
}

@Preview
@Composable
private fun OrderScreenPreview() {
    PeklaTourTheme {
        OrderScreen(
            order = listOf(),
            orderOnClick = {},
        )
    }
}