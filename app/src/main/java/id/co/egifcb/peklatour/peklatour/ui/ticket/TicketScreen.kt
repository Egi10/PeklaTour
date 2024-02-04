package id.co.egifcb.peklatour.peklatour.ui.ticket

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.component.utils.TicketShape
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme
import id.co.egifcb.peklatour.peklatour.until.formatRupiah
import net.glxn.qrgen.android.QRCode

@Composable
fun TicketScreen(
    noOrder: String,
    tourFee: String,
    destinationTour: String,
    totalPassenger: String,
    durationTour: String,
    imageQR: Bitmap,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = TicketShape(24f)
                )
                .background(
                    color = MaterialTheme.colors.primary
                )
                .padding(
                    all = 24.dp
                )
        ) {
            DoubleText(
                title = stringResource(R.string.order_no),
                subTitle = noOrder,
                colorTitle = Color.White,
                colorSubTitle = Color.White,
                modifier = Modifier
                    .weight(1f)
            )

            DoubleText(
                title = stringResource(R.string.tour_fee),
                subTitle = tourFee.formatRupiah() ?: "",
                colorTitle = Color.White,
                colorSubTitle = Color.White,
                modifier = Modifier
                    .weight(1f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = TicketShape(24f)
                )
                .background(
                    color = Color(0x9FFFFFFF)
                )
                .padding(
                    all = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = destinationTour,
                style = PeklaTourTheme.typography.title,
                fontSize = 18.sp
            )

            Spacer(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            )

            Divider()

            Spacer(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                DoubleText(
                    title = stringResource(R.string.total_passenger),
                    subTitle = totalPassenger,
                    modifier = Modifier
                        .weight(1f),
                )

                DoubleText(
                    title = stringResource(R.string.tour_duration),
                    subTitle = durationTour,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Spacer(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            )

            Divider()

            Spacer(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            )

            Image(
                bitmap = imageQR.asImageBitmap(),
                contentDescription = destinationTour
            )
        }
    }
}

@Composable
fun DoubleText(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    colorTitle: Color = Color.Unspecified,
    colorSubTitle: Color = Color.Unspecified,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = PeklaTourTheme.typography.caption,
            fontSize = 16.sp,
            color = colorTitle,
            textAlign = TextAlign.Center
        )

        Text(
            text = subTitle,
            style = PeklaTourTheme.typography.caption,
            fontSize = 16.sp,
            color = colorSubTitle
        )
    }
}

@Preview
@Composable
fun TicketScreenPreview() {
    PeklaTourTheme {
        TicketScreen(
            noOrder = "27",
            tourFee = "100000",
            destinationTour = "Air Terjun Sarasah",
            totalPassenger = "5",
            durationTour = "10",
            imageQR = Bitmap.createBitmap(
                QRCode.from("Air Terjun")
                    .withSize(450, 450)
                    .bitmap()
            )
        )
    }
}